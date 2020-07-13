package com.meipiao.ctrip.entity.response.city;

import com.meipiao.ctrip.entity.response.city.Coordinates;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 全量城市信息
 *
 * @Author: Chenwx
 * @Date: 2020/6/9 11:25
 */
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
    private String id;
    private String cityID;
    private String cityName;
    private String cityEnName;
    private String parentCityID;
    private String parentCityName;
    private String parentCityEnName;
    private String provinceID;
    private String provinceName;
    private String provinceEnName;
    private String countryID;
    private String countryName;
    private String countryEnName;
    private String continentID;
    private String continentEnName;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String continentName;
    private long updateTimeStamp;

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

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getParentCityID() {
        return parentCityID;
    }

    public void setParentCityID(String parentCityID) {
        this.parentCityID = parentCityID;
    }

    public String getParentCityName() {
        return parentCityName;
    }

    public void setParentCityName(String parentCityName) {
        this.parentCityName = parentCityName;
    }

    public String getParentCityEnName() {
        return parentCityEnName;
    }

    public void setParentCityEnName(String parentCityEnName) {
        this.parentCityEnName = parentCityEnName;
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

    public String getProvinceEnName() {
        return provinceEnName;
    }

    public void setProvinceEnName(String provinceEnName) {
        this.provinceEnName = provinceEnName;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEnName() {
        return countryEnName;
    }

    public void setCountryEnName(String countryEnName) {
        this.countryEnName = countryEnName;
    }

    public String getContinentID() {
        return continentID;
    }

    public void setContinentID(String continentID) {
        this.continentID = continentID;
    }

    public String getContinentEnName() {
        return continentEnName;
    }

    public void setContinentEnName(String continentEnName) {
        this.continentEnName = continentEnName;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public long getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(long updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}
