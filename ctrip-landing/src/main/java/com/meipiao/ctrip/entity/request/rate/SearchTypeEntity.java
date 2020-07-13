package com.meipiao.ctrip.entity.request.rate;

import java.io.Serializable;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/8 12:40
 */
public class SearchTypeEntity implements Serializable {
    /**
     * 国内城市/酒店(包含港澳台)，则传值
     * “WirelessSearch”;
     * <p>
     * 国外城市/酒店，则传值“OnLineIntlGTASearch”;
     */
    private String SearchType = "WirelessSearch";
    /**
     * 定义单页最大返回几家酒店，上限为100
     */
    private Integer HotelCount = 100;

    //指定返回酒店列表的页码，留空则返回第一页
    private Integer PageIndex = 1;

    public String getSearchType() {
        return SearchType;
    }

    public void setSearchType(String searchType) {
        SearchType = searchType;
    }

    public Integer getHotelCount() {
        return HotelCount;
    }

    public void setHotelCount(Integer hotelCount) {
        HotelCount = hotelCount;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }
}
