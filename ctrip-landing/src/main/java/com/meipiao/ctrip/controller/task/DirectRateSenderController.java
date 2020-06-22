package com.meipiao.ctrip.controller.task;

import com.meipiao.ctrip.constant.RabbitConstant;
import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.entity.mq.MQParams;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
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
@Api(value = "Direct Price RabbitMQ Task Start", tags = {"开启RabbitMQ`直连价格`队列"})
public class DirectRateSenderController {
    @Autowired
    StaticDataController staticDataController;//消息接收者(处理)

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MongoAggregationUtil mongoAggregationUtil;

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
}
