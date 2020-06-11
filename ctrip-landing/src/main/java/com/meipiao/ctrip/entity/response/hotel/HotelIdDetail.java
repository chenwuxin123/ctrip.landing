package com.meipiao.ctrip.entity.response.hotel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 酒店清单
 *
 * @Author: Chenwx
 * @Date: 2020/6/10 15:03
 */
@Document(collection = "HotelIdDetail")
public class HotelIdDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String Id;
    private String CityID;
    private String CityName;
    private String ProvinceID;
    private String ProvinceName;
    private String HotelId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public String getHotelId() {
        return HotelId;
    }

    public void setHotelId(String hotelId) {
        HotelId = hotelId;
    }

    @Override
    public String toString() {
        return "HotelIdDetail{" +
                "Id='" + Id + '\'' +
                ", CityID='" + CityID + '\'' +
                ", CityName='" + CityName + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", ProvinceName='" + ProvinceName + '\'' +
                ", HotelId='" + HotelId + '\'' +
                '}';
    }
}
