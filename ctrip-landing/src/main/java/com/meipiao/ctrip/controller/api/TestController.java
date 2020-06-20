package com.meipiao.ctrip.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: Chenwx
 * @Date: 2020/6/19 15:57
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    StaticDataController staticDataController;

    @GetMapping("/get")
    public String getHotel() {
        String MasterHotelId = "5095062";
        ArrayList<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        list.add(MasterHotelId);
        try {
            staticDataController.queryRate(list,"2020-06-20","2020-06-22");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "消耗了" + (System.currentTimeMillis() - start) + "ms";
    }

}
