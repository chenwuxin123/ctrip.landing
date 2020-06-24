package com.meipiao.ctrip.constant;

/**
 * @Author: Chenwx
 * @Date: 2020/6/9 14:24
 */
public interface RedisKeyConstant {
    String TOKEN_KEY = "ctrip:token";//过期key

    String PERSISTENCE_TOKEN_KEY = "ctrip:persistence:token";//持久化key

    String INCREMENT_START_TIME_KEY = "ctrip:increment:start";//携程增量请求StartTime key

    String INCREMENT_HOTELIDS_KEY = "ctrip:increment:hotelid";//携程增量的酒店id
}
