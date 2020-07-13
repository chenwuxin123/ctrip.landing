package com.meipiao.ctrip.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/6 6:30 下午
 */
@Data
public class HotelVo implements Serializable {

    private static final long serialVersionUID = -6129601228338951505L;

    //省份(新增)
    private String province;

    /** 酒店ID **/
    private Long hotelId;

    /** 入住日期 **/
    private String checkInDate;

    /** 离店日期 **/
    private String checkOutDate;

    /** 酒店星级
     * 19 五星级
     * 29 豪华型
     * 39 四星级
     * 49 高档型
     * 59 三星级
     * 64 舒适型
     * 69 二星级
     * 66 经济型
     * 79 二星级以下/公寓 **/
    private Integer hotelStar;

    /** 多条件酒店星级 **/
    private List<Integer> stars;

    /** 城市 **/
    private String city;

    /** 城市中文名 **/
    private String cityName;

    /** 行政区 **/
    private String distinct;

    /** 商业区 **/
    private String business;

    /** 经度 **/
    private Double longitude;

    /** 纬度 **/
    private Double latitude;

    /** 酒店中文名 **/
    private String hotelName;

    /** 页码 **/
    private Integer pageCount;

    /** 每页条数 **/
    private Integer count;

    /** 距离 **/
    private Integer distance;

    /** 品牌ID **/
    private String plateID;

    /** 集团ID **/
    private String parentHotelGroup;

    /** 最高价格 **/
    private Integer maxMoney = 999999;

    /** 最低价格 **/
    private Integer minMoney = 1;

    /** 查询时间(Date) **/
    private Date searchDateRe;

    /** 查询时间(Long) **/
    private Long searchDate;
    
    /** token **/
    private String token;

    /** 出差申请单号 **/
    private String travelNo;

    /** 高价限制 **/
    private Integer highLimit;
    
    /** 是否拆标0否1是 **/
    private Short isDismantling;

    /** 限定协议酒店.0=否，1=是 **/
    private Short contractHotel;
    
    /** 出行人集合 **/
    private String passengers;
    
    /** 预订酒店 **/
    private List<Map<String,Object>> pmap;
    
    /** 酒店行程单ID **/
    private Long applyHoteId;

    /** 排序方式 **/
    private String orderBy;

    /** 查询前提 1:协议酒店 **/
    private Integer searchPremise;


}
