package com.meipiao.ctrip.entity.response.hotel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 酒店清单
 *
 * @Author: Chenwx
 * @Date: 2020/6/10 15:03
 */
public class HotelIdDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String id;
    private String cityID;
    private String cityName;
    private String provinceID;
    private String provinceName;
    private String hotelId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
