package com.meipiao.ctrip.constant;

/**
 * 队列参数池
 *
 * @Author: Chenwx
 * @Date: 2020/6/20 16:18
 */
public interface RabbitConstant {
     //直连查询队列
     String DIRECT_RATE_QUEUE = "ctrip_pull_rate";
     //直连查询交换机
     String DIRECT_RATE_EXCHANGE = "java.ctrip";
     //直连查询路由键
     String DIRECT_RATE_ROUTINGKEY = "CtripDriectRate";

     //获取静态房型队列
     String ROOM_STATIC_QUEUE = "ctrip_pull_room_static";
    //获取静态房型交换机
    String ROOM_STATIC_EXCHANGE = "java.ctrip";
    //获取静态房型路由键
    String ROOM_STATIC_ROUTINGKEY = "CtripRoomStatic";

    //携程增量价格队列
    String INCREMENT_PRICE_QUEUE = "ctrip_pull_increment_price";
    //携程增量价格交换机
    String INCREMENT_PRICE_EXCHANGE = "java.ctrip";
    //携程增量价格路由键
    String INCREMENT_PRICE_ROUTINGKEY = "CtripIncrementPrice";
}
