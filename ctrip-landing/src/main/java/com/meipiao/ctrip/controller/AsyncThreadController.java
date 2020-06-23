package com.meipiao.ctrip.controller;

import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 11:32
 */
@Slf4j
@RestController
@RequestMapping("/task")
@Api(value = "Asynchronous Thread Task", tags = {"多线程异步拉取数据"})
public class AsyncThreadController {

    @Resource
    MongoAggregationUtil mongoAggregationUtil;

    @Resource
    StaticDataController staticDataController;

    @GetMapping("/async/static/hotel")
    @ApiOperation(value = "酒店静态信息异步拉取")
    @ApiImplicitParam(value = "线程数", required = true)
    public void asyncStaticHotel(int threadCount) {

        //threadCount线程数

        //创建线程池
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        /*
            根据需要拉取的数据平均分配给每个线程(例如3个线程总共查询到共有300个酒店id待拉取，每个线程分配100个酒店id 实现异步拉取数据)
            线程数可更改
         */

        //查找酒店id
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        //解析list 均等分给线程
        int total = list.size();
        int copy = total / threadCount;
        for (int i = 0; i < threadCount; i++) {
            List<String> subList;
            if (i != threadCount - 1) {
                subList = list.subList(copy * i, copy * (i + 1));// fromIndex（包括 ）和 toIndex（不包括）
            } else {
                subList = list.subList(copy * i, total);
            }
            executorService.execute(() -> {
                try {
                    log.info("酒店静态信息异步拉取开始执行...");
                    staticDataController.getHotelStatic(subList);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            });
        }
        executorService.shutdown();// 关闭线程池
    }

    @GetMapping("/async/static/room")
    @Deprecated
    @ApiOperation(value = "房型静态信息异步拉取")
    @ApiImplicitParam(value = "线程数", required = true)
    public void asyncStaticRooM(int threadCount) {

        //创建线程池
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        //查找酒店id
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        //解析list 均等分给线程
        int total = list.size();
        int copy = total / threadCount;
        for (int i = 0; i < threadCount; i++) {
            List<String> subList;
            if (i != threadCount - 1) {
                subList = list.subList(copy * i, copy * (i + 1));// fromIndex（包括 ）和 toIndex（不包括）
            } else {
                subList = list.subList(copy * i, total);
            }

            for (String hotelId : subList) {
                executorService.execute(() -> {
                    try {
                        log.info("房型静态信息异步拉取开始执行...");
                        staticDataController.getRoomStatic(hotelId);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });
            }
        }
        executorService.shutdown();
    }

    @GetMapping("/async/rate")
    @Deprecated
    @ApiOperation(value = "报价实时查询接口异步拉取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "threadCount", value = "线程数", required = true),
            @ApiImplicitParam(name = "start", value = "入住日期", required = true),
            @ApiImplicitParam(name = "end", value = "离店日期", required = true),
    })
    public void asyncRate(int threadCount, String start, String end) {

        //创建线程池
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        //查找酒店id
        ArrayList<String> list = mongoAggregationUtil.findAllHotelId("HotelId");
        //解析list 均等分给线程
        int total = list.size();
        int copy = total / threadCount;
        for (int i = 0; i < threadCount; i++) {
            List<String> subList;
            if (i != threadCount - 1) {
                subList = list.subList(copy * i, copy * (i + 1));// fromIndex（包括 ）和 toIndex（不包括）
            } else {
                subList = list.subList(copy * i, total);
            }

            for (String hotelId : subList) {
                executorService.execute(() -> {
                    try {
                        log.info("报价实时查询接口异步拉取开始执行...");
                        staticDataController.queryRate(hotelId, start, end);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });
            }
        }
        executorService.shutdown();
    }

    @GetMapping("/increment/price")
    @ApiOperation(value = "携程增量异步拉取")
    public void asyncIncrementPrice(){
        //创建线程池
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    }

    @Async
    public void test(String startTime) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "接收到的参数:"+ startTime);
        Thread.sleep(2000);//模拟执行任务的时间
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
