package com.meipiao.ctrip.controller;


import com.meipiao.ctrip.controller.api.StaticDataController;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.rate.RoomPriceRes;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
import com.meipiao.ctrip.entity.vo.Hotel;
import com.meipiao.ctrip.entity.vo.HotelTo;
import com.meipiao.ctrip.entity.vo.HotelVo;
import com.meipiao.ctrip.service.CtripPriceService;
import com.meipiao.ctrip.utils.QmisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/3 19:33
 */
@RestController
@RequestMapping("/d")
@Slf4j
public class MTestC {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    StaticDataController staticDataController;

    @Autowired
    CtripPriceService ctripPriceService;

    @GetMapping("d")
    public String getRoom() {
        Query query = new Query(Criteria.where("hotelId").is(6870949L));
        //获取房间静态信息
        List<RoomDetail> rooms = mongoTemplate.find(query, RoomDetail.class);
        for (RoomDetail room : rooms) {
            System.out.println(room.toString());
        }
        return "success";
    }

    @PostMapping("/test")
    public HotelTo test(HotelVo hotelVo) throws InterruptedException {
        HotelTo hotelTo = new HotelTo();

        Long hotelId = hotelVo.getHotelId();
        String start = hotelVo.getCheckInDate();
        String end = hotelVo.getCheckOutDate();
        Integer maxMoney = hotelVo.getMaxMoney();
        Integer minMoney = hotelVo.getMinMoney();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Integer diff = 0;
        try {
            long d1 = formater.parse(end).getTime();
            long d2 = formater.parse(start).getTime();
            diff = Math.toIntExact((d1 - d2) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            throw new QmisException("获取预定信息", "0108", "住离时间格式错误!!!!");
        }

        Query query = new Query(Criteria.where("hotelId").is(hotelId));
        HotelDetail hotelDetail = mongoTemplate.findOne(query, HotelDetail.class);

        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel hotel = new Hotel();
        hotel.setCheckInTime(start);
        hotel.setCheckOutTime(end);

        BeanUtils.copyProperties(hotelDetail, hotel);
        //手动导入酒店信息
        hotel.setHotelStar(starAdapter(hotelDetail.getHotelStar()));
        hotel.setHotelImage(hotelDetail.getPictures());
        hotel.setHotelFacilitys(hotelDetail.getHotelFacilitys());
        hotel.setHotelPolicys(hotelDetail.getHotelPolicys());

        //查询到直连价格
        List<RoomPriceRes> roomPriceRes = staticDataController.queryRate(hotelId.toString(), start, end);

        //获取房间静态信息
        List<RoomDetail> rooms = ctripPriceService.findRoom(hotelId);

        //匹配价格
        for (RoomDetail room : rooms) {
            List<SubRoomDetail> subRoomDetails = room.getSubRoom();
            for (SubRoomDetail subRoomDetail : subRoomDetails) {
                Long subRoomId = subRoomDetail.getRoomId();//物理房间id
                Long subRoomCode = subRoomDetail.getRoomCode();//售卖房间id
                //查询的价格信息进行匹配
                for (RoomPriceRes roomPriceRe : roomPriceRes) {
                    Long priceRoomId = roomPriceRe.getRoomId();//物理房间id的价格
                    Long priceRoomCode = roomPriceRe.getRoomCode();//售卖房间id的价格
                    if (subRoomId.equals(priceRoomId) && subRoomCode.equals(priceRoomCode)) {
                        //过滤价格
                        if (roomPriceRe.getTotalPrice() >= minMoney && roomPriceRe.getTotalPrice() <= maxMoney) {
                            roomPriceRe.setAveragePrice(roomPriceRe.getTotalPrice() / diff);
                            subRoomDetail.setRoomPriceRes(roomPriceRe);
                        }
                    }
                }
            }
        }
        hotel.setRoomDetails(rooms);
        hotelList.add(hotel);
        hotelTo.setHotels(hotelList);
        return hotelTo;
    }

    //星级转换器(66转3 5转19)
    private static Integer starAdapter(Integer star) {
        int resultStar = 99;
        if (star == 19 || star == 29) {
            resultStar = 5;
        } else if (star == 39 || star == 49) {
            resultStar = 4;
        } else if (star == 59 || star == 64) {
            resultStar = 3;
        } else if (star == 66 || star == 69) {
            resultStar = 2;
        } else if (star == 79) {
            resultStar = 0;
        } else if (star == 0 || star == 1) {
            resultStar = 79;
        } else if (star == 2) {
            resultStar = 69;
        } else if (star == 3) {
            resultStar = 59;
        } else if (star == 4) {
            resultStar = 39;
        } else if (star == 5) {
            resultStar = 19;
        }
        return resultStar;
    }

}
