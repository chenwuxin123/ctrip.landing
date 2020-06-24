package com.meipiao.ctrip.controller.task;

import com.meipiao.ctrip.constant.RabbitConstant;
import com.meipiao.ctrip.constant.RedisKeyConstant;
import com.meipiao.ctrip.entity.mq.MQParams;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 开启mq的任务
 *
 * @Author: Chenwx
 * @Date: 2020/6/19 15:57
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Api(value = "RabbitMQ Task Start", tags = {"开启RabbitMQ队列"})
public class RabbitMQSenderController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    MongoAggregationUtil mongoAggregationUtil;

    @Resource
    RedisUtil redisUtil;

    @GetMapping("/direct/rate")
    @ApiOperation(value = "全量发送`直连价格`MQ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "入住日期", required = true),
            @ApiImplicitParam(name = "end", value = "离店日期", required = true)
    })
    public void sendDirectRate(String start, String end) {
        //从mongodb中查询酒店id
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        MQParams params = new MQParams();
        for (String hotelId : list) {
            params.setHotelId(hotelId);
            params.setStart(start);
            params.setEnd(end);
            params.setExpiration(4000 * 1000);//过期时间
            params.setMessageId("Task_" + hotelId + UUID.randomUUID().toString().replace("-", ""));
            params.setCreatTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            rabbitTemplate.convertAndSend(RabbitConstant.DIRECT_RATE_EXCHANGE, RabbitConstant.DIRECT_RATE_ROUTINGKEY, params);
        }
    }

    @GetMapping("/direct/single/rate/")
    @ApiOperation(value = "发送某个酒店`直连价格`MQ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelId", value = "酒店id", required = true),
            @ApiImplicitParam(name = "start", value = "入住日期", required = true),
            @ApiImplicitParam(name = "end", value = "离店日期", required = true)
    })
    public void sendDirectRate(String hotelId, String start, String end) {
        MQParams params = new MQParams();
        params.setHotelId(hotelId);
        params.setStart(start);
        params.setEnd(end);
        params.setExpiration(4000 * 1000);//过期时间
        params.setMessageId("Task_" + hotelId + UUID.randomUUID().toString().replace("-", ""));
        params.setCreatTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        rabbitTemplate.convertAndSend(RabbitConstant.DIRECT_RATE_EXCHANGE, RabbitConstant.DIRECT_RATE_ROUTINGKEY, params);
    }

    @GetMapping("/room/static")
    @ApiOperation(value = "全量发送`静态房型`MQ")
    public void sendRoomStatic() {
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        MQParams params = new MQParams();
        for (String hotelId : list) {
            params.setHotelId(hotelId);
            params.setExpiration(4000 * 1000);//过期时间
            params.setMessageId("Task_" + hotelId + UUID.randomUUID().toString().replace("-", ""));
            params.setCreatTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            rabbitTemplate.convertAndSend(RabbitConstant.ROOM_STATIC_EXCHANGE, RabbitConstant.ROOM_STATIC_ROUTINGKEY, params);
        }
    }

    /**
     * 将得到的HotelIds进行过滤(1.传递的参数去重 2.未使用的抛弃 3.5S内查询过的抛弃)
     * 将过滤后的HotelIds逐个发送至队列，进行全量拉取
     * 已发送的HotelId
     * redis 为hash类型 key-hotelId-timeStamp
     */
    private static AtomicLong uniqueseed = new AtomicLong(System.currentTimeMillis() / 1000);

    public void sendIncrementPrice(List<String> hotelIds) {
        long enterTime = uniqueseed.incrementAndGet();//进入redis的时间
        /*
              1.传递的参数去重
         */
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(hotelIds);
        /*
               2.未使用的抛弃 (连接中心库进行过滤)
               .....
         */
        String creatTime;
        for (String hotelId : hashSet) {
            /*
                    3.5S内查询过的抛弃
             */
            if (!redisUtil.hasKey(RedisKeyConstant.INCREMENT_HOTELIDS_KEY)) {
                redisUtil.hset(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, "test", "test");
            }
            boolean hasHotelId = redisUtil.hHasKey(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, hotelId);
            if (!hasHotelId) {
                //如果不存在 先存在发
                creatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
                redisUtil.hset(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, hotelId, enterTime);
                sendDirectRate(hotelId, creatTime, creatTime);
            } else {
                //如果存在 获取上一次的时间戳
                Object getTimeStamp = redisUtil.hget(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, hotelId);
                long lastTimeStamp = Long.parseLong(String.valueOf(getTimeStamp));
                if (enterTime - lastTimeStamp > 5000) {
                    //间隔大于5s 先改在发
                    creatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
                    redisUtil.hdel(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, hotelId);
                    redisUtil.hset(RedisKeyConstant.INCREMENT_HOTELIDS_KEY, hotelId, enterTime);
                    sendDirectRate(hotelId, creatTime, creatTime);
                }
            }
        }
    }
}
