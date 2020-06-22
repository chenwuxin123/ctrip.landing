package com.meipiao.ctrip.monitor;

import com.meipiao.ctrip.constant.RabbitConstant;
import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.entity.mq.MQParams;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者
 *
 * @Author: Chenwx
 * @Date: 2020/6/20 14:09
 */
//直连价格监听
@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(RabbitConstant.DIRECT_RATE_QUEUE),
        exchange = @Exchange(RabbitConstant.DIRECT_RATE_EXCHANGE),
        key = {RabbitConstant.DIRECT_RATE_ROUTINGKEY}
))
public class DirectRateMonitor {

    @Autowired
    StaticDataController staticDataController;

    @RabbitHandler
    public void processDirectRate(MQParams params, Message message, Channel channel) throws IOException {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            staticDataController.queryRate(params.getHotelId(), params.getStart(), params.getEnd());
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //异常处理
            System.out.println("处理失败的id为" + params.getHotelId());
            //deliveryTag:该消息的index multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息 requeue：被拒绝的是否重新入队列 注意：如果设置为true ，则会添加在队列的末端
            channel.basicNack(deliveryTag, false, false);
        }
    }
}
