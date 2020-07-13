package com.meipiao.ctrip.entity.response.hotel;

import com.alibaba.fastjson.annotation.JSONField;
import com.meipiao.ctrip.entity.response.city.Coordinates;
import com.meipiao.ctrip.entity.vo.hotel.HotelFacility;
import com.meipiao.ctrip.entity.vo.hotel.HotelPolicy;
import com.meipiao.ctrip.entity.vo.imge.Image;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 9:46
 */
@Data
public class HotelDetail implements Serializable {
    private Long hotelId;
    private Integer dataFlag;
    /** -酒店中文名- **/
    private String hotelName;
    /** -酒店英文名- **/
    private String hotelEngName;
    /** -酒店地址- **/
    private String address;
    /** -酒店英文地址- **/
    private String addressEn;
    /** -酒店介绍- **/
    private String hotelIntroduce;
    /** -酒店电话- **/
    private String telephone;
    /** -酒店传真- **/
    private String fax;
    /** -邮政编码- **/
    private String postCode;
    /** -酒店星级- **/
    private Integer hotelStar;
    /** -开业日期(String)- **/
    private String praciceDate;
    /** -城市名称-**/
    private String cityName;
    /** -酒店房间总数- **/
    private String roomNum;
    /** -酒店图片- **/
    private List<Image> pictures;
    /** -商业区名称- **/
    private String businessName;

    /** -酒店设施- **/
    private List<HotelFacility> hotelFacilitys;
    /** -酒店政策- **/
    private List<HotelPolicy> hotelPolicys;

    private Integer isOfficialRating; //1:true 0:false

    private List<Coordinates> coordinates;

    private String countryName;

    private String provinceName;

    private String email;
    /** 酒店周边交通信息：包括POI经纬度、POI名称、POI类型、距离酒店直线距离、交通信息描述、交通类型（驾车或步行等）、花费时间 **/
    private List<TransportationInfos> transportationInfos;

    /** 酒店房型 **/
    @JSONField(serialize = false)
    private List<Long> roomInfos;
    /** 最低价 **/
    @JSONField(serialize = false)
    private Double lowestPrice;

}
