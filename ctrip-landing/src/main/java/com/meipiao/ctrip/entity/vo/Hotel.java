package com.meipiao.ctrip.entity.vo;

import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.vo.hotel.HotelAround;
import com.meipiao.ctrip.entity.vo.hotel.HotelFacility;
import com.meipiao.ctrip.entity.vo.hotel.HotelPolicy;
import com.meipiao.ctrip.entity.vo.imge.Image;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/4 4:47 下午
 */
@Data
public class Hotel implements Serializable {

    private static final long serialVersionUID = -1920191879305489193L;

    private Long hotelId;
    /** 酒店中文名 **/
    private String hotelName;
    /** 酒店英文名 **/
    private String hotelEngName;
    /** 酒店地址 **/
    private String address;
    /** 酒店介绍 **/
    private String hotelIntroduce;
    /** 酒店电话 **/
    private String telephone;
    /** 酒店传真 **/
    private String fax;
    /** 邮政编码 **/
    private String postCode;
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
    /** 开业日期(String) **/
    private String praciceDate;
    /** 开业日期(Data) **/
    private Date praciceDateT;
    /** 装修日期(String) **/
    private String fitmentDate;
    /** 装修日期(Data) **/
    private Date fitmentDateT;
    /** 酒店集团ID **/
    private String parentHotelGroup;
    /** 酒店集团名称 **/
    private String parentHotelGroupName;
    /** 所属品牌ID **/
    private String plateID;
    /** 品牌名称 **/
    private String plateName;
    /** 城市代码 **/
    private String city;
    /** 城市名称 **/
    private String cityName;
    /** 行政区代码 **/
    private String distinct;
    /** 行政区名称 **/
    private String distinctName;
    /** 商业区代码 **/
    private String business;
    /** 商业区名称 **/
    private String businessName;
    /** 经度 **/
    private Double longitude;
    /** 纬度 **/
    private Double latitude;
    /** 酒店规定入住 **/
    private String checkInTime;
    /** 酒店规定退房 **/
    private String checkOutTime;
    /** 酒店房间总数 **/
    private String roomNum;
    /** 酒店主图地址 **/
    private String appearancePicUrl;
    /** 酒店设施 **/
    private List<HotelFacility> hotelFacilitys;
    /** 酒店政策 **/
    private List<HotelPolicy> hotelPolicys;
    /** 酒店周边 **/
    private List<HotelAround> hotelArounds;
    /** 酒店房型 **/
    private List<Long> roomInfos;
    /** 直线距离(千米) **/
    private Double distance;
    /** 最低价 **/
    private Double lowestPrice;
    /** 酒店产品列表 **/
    private List<String> roomItems;
    /** 酒店图片 **/
    private List<Image> hotelImage;
    /** 渠道来源 **/
    private String channel;

    private List<RoomDetail> roomDetails;
}
