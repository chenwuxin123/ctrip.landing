package com.meipiao.ctrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Chenwx
 * @Date: 2020/6/6 16:59
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/get")
    public String get(){

        return "success";
    }
}
