package com.meipiao.ctrip.controller;

import com.google.common.collect.Sets;
import com.meipiao.ctrip.constant.RedisKeyConstant;
import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 11:32
 */
@Slf4j
@RestController
@RequestMapping("/async")
@Api(value = "Asynchronous Thread Task", tags = {"多线程异步拉取数据"})
public class AsyncThreadController {

    @Resource
    MongoAggregationUtil mongoAggregationUtil;

    @Resource
    StaticDataController staticDataController;

    @Resource
    RedisUtil redisUtil;

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;//线程池

    @GetMapping("/static/hotel")
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

    @GetMapping("/static/room")
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

    @GetMapping("/rate")
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
    @SuppressWarnings("InfiniteLoopStatement")
    public void asyncIncrementPrice() throws InterruptedException {
        String startTime = "";
        String nextTime = "";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int count = 0;
        long start = System.currentTimeMillis() / 1000;

        //判断是否存在start time key
        String crtipIncreStartTimeKey = RedisKeyConstant.INCREMENT_START_TIME_KEY;
        if (!redisUtil.hasKey(crtipIncreStartTimeKey)) {
            //获取当前时间 向后推迟30s
            LocalDateTime time = LocalDateTime.now().minusSeconds(30L);
            startTime = df.format(time);
            redisUtil.set(crtipIncreStartTimeKey, startTime);
        }
        log.info("Ctrip Increment price channel starting...");
        while (true) {
            startTime = redisUtil.get(crtipIncreStartTimeKey).toString();
            //执行异步任务
            String findTime = startTime;
            taskExecutor.submit(() -> {
                try {
                    Future future = staticDataController.changePrice(findTime);
                    //获取返回值
                    Object getTimeStamp = future.get();
                    long resultTimeStamp = Long.parseLong(String.valueOf(getTimeStamp));
                } catch (InterruptedException | ExecutionException e) {
                    log.error("{}时间段拉取增量发生异常，异常原因:{}", findTime, e.getMessage());
                    //记录时间段，人工处理
                }
            });

            Thread.sleep(800);//延迟0.8s
            //更新缓存时间 +1s
            LocalDateTime plusSecond = LocalDateTime.parse(startTime, df).plusSeconds(1L);
            nextTime = df.format(plusSecond);
            redisUtil.set(crtipIncreStartTimeKey, nextTime);
            count++;
            while (System.currentTimeMillis() / 1000 - start > 60) {
                log.info("过去每分钟共消费{}条数据", count);
                count = 0;
                //获取返回的时间戳


                start = System.currentTimeMillis();
            }
        }

    }

}
