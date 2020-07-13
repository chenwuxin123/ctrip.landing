package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:15
 */
public class AvailRequestSegment {
    private HotelSearchCriteria HotelSearchCriteria;

    public void setHotelSearchCriteria(HotelSearchCriteria HotelSearchCriteria){
        this.HotelSearchCriteria = HotelSearchCriteria;
    }
    public HotelSearchCriteria getHotelSearchCriteria(){
        return this.HotelSearchCriteria;
    }

}
