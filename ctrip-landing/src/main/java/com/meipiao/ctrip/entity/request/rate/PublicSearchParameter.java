package com.meipiao.ctrip.entity.request.rate;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/8 12:54
 */
public class PublicSearchParameter implements Serializable {
    /**
     * 城市ID，搜索该城市下所有可售酒店的起价。City和HotelList必有一个需要进行传值。
     */
    private Integer City;
    private String CheckInDate;
    private String CheckOutDate;
    /**
     * 酒店列表，逗号隔开,建议单次请求不超过20个酒店ID。
     * 备注：不使用位置搜索的情况下，必须对City或HotelList进行传值。
     */
    private String HotelList = "";
    /**
     * 根据房型入住人数限制筛选房型。
     *
     * 传单个数字N，表示仅返回最大入住人数限制为N的房型。
     *
     * 传 ”N,999”，表示返回入住人数为N或N以上的房型。
     */
    private String FilterRoomByPerson = "1,999";
    /**
     * 是否只返回预付房型最低价。True-只返回预付房型；False-不进行过滤。OnlyPPPrice与OnlyFGprice 不可同时为true。
     */
    private Boolean OnlyPPPrice = true;
    /**
     * 是否只返回到付房型最低价。True-只返回到付房型；False-不进行过滤。OnlyPPPrice和OnglFGprice不可同时为true。
     */
    private Boolean OnlyFGPrice = false;
    /**
     * 是否只返回可立即确认的房型。T-只返回立即确认房型；F-只返回不可立即确认的房型。
     */
    private String IsJustifyConfirm = "T";

    public Integer getCity() {
        return City;
    }

    public void setCity(Integer city) {
        City = city;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public String getHotelList() {
        return HotelList;
    }

    public void setHotelList(String hotelList) {
        HotelList = hotelList;
    }

    public String getFilterRoomByPerson() {
        return FilterRoomByPerson;
    }

    public void setFilterRoomByPerson(String filterRoomByPerson) {
        FilterRoomByPerson = filterRoomByPerson;
    }

    public Boolean getOnlyPPPrice() {
        return OnlyPPPrice;
    }

    public void setOnlyPPPrice(Boolean onlyPPPrice) {
        OnlyPPPrice = onlyPPPrice;
    }

    public Boolean getOnlyFGPrice() {
        return OnlyFGPrice;
    }

    public void setOnlyFGPrice(Boolean onlyFGPrice) {
        OnlyFGPrice = onlyFGPrice;
    }

    public String getIsJustifyConfirm() {
        return IsJustifyConfirm;
    }

    public void setIsJustifyConfirm(String isJustifyConfirm) {
        IsJustifyConfirm = isJustifyConfirm;
    }
}
