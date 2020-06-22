package com.meipiao.ctrip.entity.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * 参数
 * @Author: Chenwx
 * @Date: 2020/6/20 15:23
 */
@Data
public class MQParams implements Serializable {
    //消息唯一id
    private String messageId;
    //酒店id
    private String hotelId;
    //入店时间
    private String start;
    //离店时间
    private String end;
    //过期时间
    private int expiration;
    //创建时间
    private String creatTime;
}
