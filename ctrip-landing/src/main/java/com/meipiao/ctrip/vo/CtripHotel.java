package com.meipiao.ctrip.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description 酒店详情
 * @date 2020/7/1 10:37
 */
public class CtripHotel implements Serializable {

    private static final long serialVersionUID = 113472531382682135L;

    /**
     * 酒店id
     **/
    private String masterHotelNum;
    /**
     * 酒店中文名
     **/
    private String hotelName;
    /**
     * 酒店英文名
     **/
    private String hotelNameEn;
    /**
     * 酒店地址
     **/
    private String address;
    /**
     * 酒店英文地址
     **/
    private String addressEn;
    /**
     * 酒店介绍
     **/
    private String hotelIntroduce;
    /**
     * 酒店电话
     **/
    private String telephone;
    /**
     * 酒店传真
     **/
    private String fax;
    /**
     * 邮政编码
     **/
    private String postCode;
    /**
     * 酒店星级
     **/
    private String starRating;
    /**
     * 标明星级是否有政府机构评定
     **/
    private String isOfficialRating;
    /**
     * 开业日期(String)
     **/
    private String openYear;
    /**
     * 酒店的客房数量
     **/
    private Integer roomQuantity;
    /**
     * 所在国家
     **/
    private String countryName;
    /**
     * 所在省份
     **/
    private String provinceName;
    /**
     * 所在城市
     **/
    private String cityName;
    /**
     * 商圈
     **/
    private List<String> businessDistrict;
    /**
     * 最低价 (从酒店中展示房间最低价)
     **/
    private Integer lowestPrice;

    /**
     * 酒店图片
     **/
    private List<String> hotelPicture;

    private HashSet<String> roomId;

    public HashSet<String> getRoomId() {
        return roomId;
    }

    public void setRoomId(HashSet<String> roomId) {
        this.roomId = roomId;
    }

    public List<String> getHotelPicture() {
        return hotelPicture;
    }

    public void setHotelPicture(List<String> hotelPicture) {
        this.hotelPicture = hotelPicture;
    }

    public Integer getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Integer lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public CtripHotel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMasterHotelNum() {
        return masterHotelNum;
    }

    public void setMasterHotelNum(String masterHotelNum) {
        this.masterHotelNum = masterHotelNum;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getHotelIntroduce() {
        return hotelIntroduce;
    }

    public void setHotelIntroduce(String hotelIntroduce) {
        this.hotelIntroduce = hotelIntroduce;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getIsOfficialRating() {
        return isOfficialRating;
    }

    public void setIsOfficialRating(String isOfficialRating) {
        this.isOfficialRating = isOfficialRating;
    }

    public String getOpenYear() {
        return openYear;
    }

    public void setOpenYear(String openYear) {
        this.openYear = openYear;
    }

    public Integer getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(Integer roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(List<String> businessDistrict) {
        this.businessDistrict = businessDistrict;
    }
}
