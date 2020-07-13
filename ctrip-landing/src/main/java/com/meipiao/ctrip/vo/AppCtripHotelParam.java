package com.meipiao.ctrip.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description 携程页面查询请求体
 * @date 2020/7/1 9:59
 */
public class AppCtripHotelParam implements Serializable {

    private static final long serialVersionUID = -4507338370755297631L;
    /**
     * 省份(id 省份名称---->1 北京)
     */
    private String province;

    /**
     * 城市(id 城市名称---->12 南京)
     */
    private String city;

    /**
     * 星级 (59 三星级)
     */
    private Integer starRating;

    /**
     * 入住日期
     */
    private String checkInDate;

    /**
     * 离店日期
     */
    private String checkOutDate;

    /**
     * 最高价格
     */
    private Integer maxMoney;

    /**
     * 最低价格
     */
    private Integer minMoney;

    /**
     * 入住人
     */
    private List<String> travelers;

    /**
     * 当前页
     *
     */
    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    public List<String> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<String> travelers) {
        this.travelers = travelers;
    }
}
