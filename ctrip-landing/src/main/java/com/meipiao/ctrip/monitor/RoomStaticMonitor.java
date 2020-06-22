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
 * @Author: Chenwx
 * @Date: 2020/6/22 9:35
 */
//房型静态信息(物理房型,子房型
@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(RabbitConstant.ROOM_STATIC_QUEUE),
        exchange = @Exchange(RabbitConstant.ROOM_STATIC_EXCHANGE),
        key = {RabbitConstant.ROOM_STATIC_ROUTINGKEY}
))
public class RoomStaticMonitor {
    @Autowired
    StaticDataController staticDataController;

    @RabbitHandler
    public void processRoomStatic(MQParams params, Message message, Channel channel) throws IOException {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            staticDataController.getRoomStatic(params.getHotelId());
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
