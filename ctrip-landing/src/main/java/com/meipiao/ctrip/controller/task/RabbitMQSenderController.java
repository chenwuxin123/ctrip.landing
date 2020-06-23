package com.meipiao.ctrip.controller.task;

import com.meipiao.ctrip.constant.RabbitConstant;
import com.meipiao.ctrip.constant.RedisKeyConstant;
import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.entity.mq.MQParams;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;


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

    private final String crtipIncreStartTimeKey = RedisKeyConstant.INCREMENT_START_TIME_KEY;

    @GetMapping("/direct/rate")
    @ApiOperation(value = "发送`直连价格`MQ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "入住日期", required = true),
            @ApiImplicitParam(name = "end", value = "离店日期", required = true)
    })
    public void sendDirectRate(String start, String end) {
        //从mongodb中查询酒店id
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        for (String hotelId : list) {
            MQParams params = new MQParams();
            params.setHotelId(hotelId);
            params.setStart(start);
            params.setEnd(end);
            params.setExpiration(4000 * 1000);//过期时间
            params.setMessageId("Task_" + hotelId + UUID.randomUUID().toString().replace("-", ""));
            params.setCreatTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            rabbitTemplate.convertAndSend(RabbitConstant.DIRECT_RATE_EXCHANGE, RabbitConstant.DIRECT_RATE_ROUTINGKEY, params);
        }
    }

    @GetMapping("/room/static")
    @ApiOperation(value = "发送`静态房型`MQ")
    public void sendRoomStatic() {
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        for (String hotelId : list) {
            MQParams params = new MQParams();
            params.setHotelId(hotelId);
            params.setExpiration(4000 * 1000);//过期时间
            params.setMessageId("Task_" + hotelId + UUID.randomUUID().toString().replace("-", ""));
            params.setCreatTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            rabbitTemplate.convertAndSend(RabbitConstant.ROOM_STATIC_EXCHANGE, RabbitConstant.ROOM_STATIC_ROUTINGKEY, params);
        }
    }

    @GetMapping("/increment/price")
    @ApiOperation(value = "发送`价格增量`MQ")
    public void sendIncrementPrice() {
        String startTime = "";
        String nextTime = "";
        boolean hasKey = redisUtil.hasKey(crtipIncreStartTimeKey);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int count = 0;
        long start = System.currentTimeMillis() / 1000;
        log.info("Ctrip Increment price channel starting...");
        while (true) {
            if (hasKey) {
                startTime = redisUtil.get(crtipIncreStartTimeKey).toString();
                //发送MQ
                rabbitTemplate.convertAndSend(RabbitConstant.INCREMENT_PRICE_EXCHANGE, RabbitConstant.INCREMENT_PRICE_ROUTINGKEY, startTime);
                //更新缓存时间 +1s
                LocalDateTime plusSecond = LocalDateTime.parse(startTime, df).plusSeconds(1L);
                nextTime = df.format(plusSecond);
                redisUtil.set(crtipIncreStartTimeKey, nextTime);
                count++;
                while (System.currentTimeMillis() / 1000 - start > 30) {
                    log.info("过去半分钟共入队{}条数据", count);
                    start = System.currentTimeMillis();
                }
            } else {
                //获取当前时间
                LocalDateTime time = LocalDateTime.now().minusSeconds(30L);//向后推迟30s
                startTime = df.format(time);
                //发送MQ
                rabbitTemplate.convertAndSend(RabbitConstant.INCREMENT_PRICE_EXCHANGE, RabbitConstant.INCREMENT_PRICE_ROUTINGKEY, startTime);
                //进缓存的时间 +1s
                nextTime = df.format(time.plusSeconds(1L));
                redisUtil.set(crtipIncreStartTimeKey, nextTime);
                hasKey = true;
                count++;
            }
        }
    }
}
