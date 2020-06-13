package com.meipiao.ctrip.entity.response.city;

import com.meipiao.ctrip.entity.response.city.Coordinates;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 全量城市信息
 * @Author: Chenwx
 * @Date: 2020/6/9 11:25
 */
@Document(collection = "Destination")
public class Destination implements Serializable {
    /**
     * CityID : 1
     * CityName : 北京
     * CityEnName : Beijing
     * ParentCityID :
     * ParentCityName :
     * ParentCityEnName :
     * ProvinceID : 1
     * ProvinceName : 北京
     * ProvinceEnName : Beijing
     * CountryID : 1
     * CountryName : 中国
     * CountryEnName : China
     * ContinentID : 1
     * ContinentEnName : Asia
     */
    @org.springframework.data.annotation.Id
    private String Id;
    private String CityID;
    private String CityName;
    private String CityEnName;
    private String ParentCityID;
    private String ParentCityName;
    private String ParentCityEnName;
    private String ProvinceID;
    private String ProvinceName;
    private String ProvinceEnName;
    private String CountryID;
    private String CountryName;
    private String CountryEnName;
    private String ContinentID;
    private String ContinentEnName;
    private List<Coordinates> Coordinates = new ArrayList<>();
    private String ContinentName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContinentName() {
        return ContinentName;
    }

    public void setContinentName(String continentName) {
        ContinentName = continentName;
    }

    public long getUpdateTimeStamp() {
        return UpdateTimeStamp;
    }

    public void setUpdateTimeStamp(long updateTimeStamp) {
        UpdateTimeStamp = updateTimeStamp;
    }

    private long UpdateTimeStamp;

    public List<com.meipiao.ctrip.entity.response.city.Coordinates> getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(List<com.meipiao.ctrip.entity.response.city.Coordinates> coordinates) {
        Coordinates = coordinates;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String CityID) {
        this.CityID = CityID;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getCityEnName() {
        return CityEnName;
    }

    public void setCityEnName(String CityEnName) {
        this.CityEnName = CityEnName;
    }

    public String getParentCityID() {
        return ParentCityID;
    }

    public void setParentCityID(String ParentCityID) {
        this.ParentCityID = ParentCityID;
    }

    public String getParentCityName() {
        return ParentCityName;
    }

    public void setParentCityName(String ParentCityName) {
        this.ParentCityName = ParentCityName;
    }

    public String getParentCityEnName() {
        return ParentCityEnName;
    }

    public void setParentCityEnName(String ParentCityEnName) {
        this.ParentCityEnName = ParentCityEnName;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String ProvinceID) {
        this.ProvinceID = ProvinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String ProvinceName) {
        this.ProvinceName = ProvinceName;
    }

    public String getProvinceEnName() {
        return ProvinceEnName;
    }

    public void setProvinceEnName(String ProvinceEnName) {
        this.ProvinceEnName = ProvinceEnName;
    }

    public String getCountryID() {
        return CountryID;
    }

    public void setCountryID(String CountryID) {
        this.CountryID = CountryID;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    public String getCountryEnName() {
        return CountryEnName;
    }

    public void setCountryEnName(String CountryEnName) {
        this.CountryEnName = CountryEnName;
    }

    public String getContinentID() {
        return ContinentID;
    }

    public void setContinentID(String ContinentID) {
        this.ContinentID = ContinentID;
    }

    public String getContinentEnName() {
        return ContinentEnName;
    }

    public void setContinentEnName(String ContinentEnName) {
        this.ContinentEnName = ContinentEnName;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "Id=" + Id +
                ", CityID='" + CityID + '\'' +
                ", CityName='" + CityName + '\'' +
                ", CityEnName='" + CityEnName + '\'' +
                ", ParentCityID='" + ParentCityID + '\'' +
                ", ParentCityName='" + ParentCityName + '\'' +
                ", ParentCityEnName='" + ParentCityEnName + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", ProvinceName='" + ProvinceName + '\'' +
                ", ProvinceEnName='" + ProvinceEnName + '\'' +
                ", CountryID='" + CountryID + '\'' +
                ", CountryName='" + CountryName + '\'' +
                ", CountryEnName='" + CountryEnName + '\'' +
                ", ContinentID='" + ContinentID + '\'' +
                ", ContinentEnName='" + ContinentEnName + '\'' +
                ", Coordinates=" + Coordinates +
                ", ContinentName='" + ContinentName + '\'' +
                ", UpdateTimeStamp=" + UpdateTimeStamp +
                '}';
    }
}
