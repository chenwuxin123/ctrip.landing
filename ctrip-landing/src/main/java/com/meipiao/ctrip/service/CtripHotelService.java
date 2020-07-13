package com.meipiao.ctrip.service;


import com.meipiao.ctrip.entity.vo.HotelTo;
import com.meipiao.ctrip.entity.vo.HotelVo;


/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/6/30 21:14
 */
public interface CtripHotelService {

    /**
     * 酒店列表查询 - 根据需求查询符合条件的列表
     * @param hotelVo
     * @return
     */
    HotelTo findHotelInfo(HotelVo hotelVo);
}
