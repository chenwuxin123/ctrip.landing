package com.meipiao.ctrip.entity.request.hotel;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 8:26
 */
@Data
public class Settings implements Serializable {
    private String PrimaryLangID = "zh-cn";

    private List<String> ExtendedNodes = new ArrayList<String>(){
        {
            this.add("HotelStaticInfo.GeoInfo");//酒店地理信息：包括所在城市、邮编、地址、相近路口、经纬度信息
            this.add("HotelStaticInfo.TransportationInfos");//酒店周边交通信息：包括POI经纬度、POI名称、POI类型、距离酒店直线距离、交通信息描述、交通类型（驾车或步行等）、花费时间
            //this.add("HotelStaticInfo.Brand");//酒店品牌：品牌代码、品牌名称、品牌首图
            //this.add("HotelStaticInfo.Group");//酒店集团：集团代码、品牌名称
            //this.add("HotelStaticInfo.Ratings");//酒店评分：携程评级、用户综合点评分、携程用户评级（已废弃）
            this.add("HotelStaticInfo.Policies");//酒店政策：入住和离店时间、儿童政策、早餐信息、宠物
            //"HotelStaticInfo.AcceptedCreditCards",//酒店前台可用支付方式
            //"HotelStaticInfo.ImportantNotices",//酒店重要通知
            this.add("HotelStaticInfo.FacilitiesV2");//酒店设施设备：设施设备ID、设施设备状态、设施设备类型、收费信息（收费还是免费，收费明细）、位置信息（住宿内还是住宿外）、营业时间、餐厅信息、子设施信息、年龄限制（最大年龄，最小年龄）
            this.add("HotelStaticInfo.Pictures");//酒店图片：图片类型、分类描述、图片链接
            this.add("HotelStaticInfo.Descriptions");//	酒店描述：描述类型（1短描述2长描述,3已废弃）、描述文本
            this.add("HotelStaticInfo.ContactInfo");//酒店联系信息：酒店联系电话、酒店传真
            this.add("HotelStaticInfo.HotelTags.IsBookable");//IsBookable：是否可订
            //"HotelStaticInfo.ArrivalTimeLimitInfo",//酒店到店时间限制：最早时间、最晚时间、是否强制
            //"HotelStaticInfo.DepartureTimeLimitInfo",//酒店离店时间限制：最早时间、最晚时间
            //"HotelStaticInfo.ExternalFacility.Parking",//停车场信息（位置、是否预定、停车场类型、是否收费、收费标准）
            //"HotelStaticInfo.ExternalFacility.ChargingPoint",
            //"HotelStaticInfo.BossInfos",//酒店老板信息：暂不公开开放该内容
            //"HotelStaticInfo.HotelDiscount",//酒店促销规则信息
            //"HotelStaticInfo.SellerShowInfos",//酒店秀
            //"HotelStaticInfo.VideoItems",//视频秀
            //"HotelStaticInfo.NormalizedPolicies.ChildAndExtraBedPolicy", //ChildAndExtraBedPolicy：儿童政策及加床信息（是否允许儿童入住、是否允许使用现有床铺、是否加床、备注描述）
            //"HotelStaticInfo.NormalizedPolicies.MealsPolicyV2",//MealsPolicyV2：餐食信息（餐食类型、服务时间、是否供应、价格）
            this.add("HotelStaticInfo.HotelTags.ReservedData");//ReservedData：酒店英文名称
            //"HotelStaticInfo.HotelPromotions",//酒店优享会规则信息
            //"HotelStaticInfo.HotelTaxRuleInfos",//酒店税费规则
            //"HotelStaticInfo.SepecialServiceForChinese",//华人礼遇信息：只适用海外酒店
            //"HotelStaticInfo.HotelFeatures",//酒店特色标签
            //"HotelStaticInfo.HotelUsedNames",//酒店曾用名
            //"HotelStaticInfo.BnBHotel",// 获取民宿酒店专有信息
            //"HotelStaticInfo.ContactPersonInfos"//联系人信息
        }
    };


}
