package com.meipiao.ctrip.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description 查询酒店结果返回体
 * @date 2020/7/1 10:35
 */
public class CtripHotrlTo implements Serializable {

    private static final long serialVersionUID = 4416006756028017691L;

    /** 总条数 **/
    private Integer totalCount;
    /** 总页数 **/
    private Integer totalPage;
    /** 当前页数 **/
    private Integer page;
    /** 酒店列表 **/
    private List<CtripHotel> hotels;
    /** 高价限制 **/
    private Integer highLimit;
    /** 是否拆标0否1是 **/
    private Short isDismantling;
    /** 预订酒店 **/
    private List<Map<String,Object>> pmap;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<CtripHotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<CtripHotel> hotels) {
        this.hotels = hotels;
    }

    public Integer getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(Integer highLimit) {
        this.highLimit = highLimit;
    }

    public Short getIsDismantling() {
        return isDismantling;
    }

    public void setIsDismantling(Short isDismantling) {
        this.isDismantling = isDismantling;
    }

    public List<Map<String, Object>> getPmap() {
        return pmap;
    }

    public void setPmap(List<Map<String, Object>> pmap) {
        this.pmap = pmap;
    }
}
