package com.meipiao.ctrip.controller;

import com.meipiao.ctrip.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Chenwx
 * @Date: 2020/6/6 16:59
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Resource
    RedisUtil redisUtil;

    @GetMapping("/get")
    public String get(){
        Map<Object, Object> hmget = redisUtil.hmget("ctrip:token");

        return "success";
    }
}
