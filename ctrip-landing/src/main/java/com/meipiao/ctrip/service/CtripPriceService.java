package com.meipiao.ctrip.service;

import com.meipiao.ctrip.entity.response.room.RoomDetail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/1 11:42
 */
public interface CtripPriceService {

    /**
    * @description  TODO(查询此酒店下最低价)
    * @param masterHotelNum 酒店id
    * @return 最低价
    */
    Double queryLowestPrice(Long masterHotelNum);

    List<RoomDetail> findRoom(Long masterHotelNum);
}
