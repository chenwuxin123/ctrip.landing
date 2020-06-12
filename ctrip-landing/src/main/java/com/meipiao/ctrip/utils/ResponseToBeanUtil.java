package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.Coordinates;
import com.meipiao.ctrip.entity.response.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.room.DateRestriction;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
import com.meipiao.ctrip.entity.response.room.TimeLimitInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 将相应结果转化成相应的Bean
 *
 * @Author: Chenwx
 * @Date: 2020/6/10 10:54
 */
public class ResponseToBeanUtil {
    public static void main(String[] args) {

        //全量城市信息
        String result = "{\"ResponseStatus\":{\"Timestamp\":\"2020-06-11T13:50:09.889+08:00\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[]},\"RoomStaticInfos\":[{\"RoomTypeInfo\":{\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"ChildLimit\":{\"MaxOccupancy\":1,\"MaxAge\":0,\"MinAge\":0},\"Facilities\":[{\"FacilityItem\":[{\"ID\":\"180\",\"Name\":\"有线频道\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"183\",\"Name\":\"电视机\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"264\",\"Name\":\"客房WIFI\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"215\",\"Name\":\"220V电压插座\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"107\",\"Name\":\"空调\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"208\",\"Name\":\"衣柜/衣橱\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"79\",\"Name\":\"24小时热水\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"},{\"FacilityItem\":[{\"ID\":\"93\",\"Name\":\"吹风机\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"}],\"Pictures\":[{\"Type\":\"6\",\"Caption\":\"大床房\",\"URL\":\"http://dimg04.c-ctrip.com/images//200b1f000001gu1124C88_R_550_412.jpg\"},{\"Type\":\"6\",\"Caption\":\"大床房\",\"URL\":\"http://dimg04.c-ctrip.com/images//280h0w000000kg08n04D1_R_550_412.jpg\"}],\"Descriptions\":[],\"RoomBedInfos\":[{\"BedInfo\":[{\"ID\":\"4060\",\"Name\":\"大床\",\"NumberOfBeds\":\"1\",\"BedWidth\":\"1.6\"}],\"ID\":\"360\",\"Name\":\"大床\"}],\"RoomTypeTags\":[],\"RoomTypeID\":95383001,\"RoomTypeName\":\"大床房\",\"StandardRoomType\":\"标准间\",\"RoomQuantity\":6,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"BathRoomType\":\"Unknown\",\"ExtraBedCurrency\":\"RMB\"},\"RoomInfos\":[{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"AreaApplicabilityInfo\":{\"Details\":[{\"ContinentID\":\"1\",\"ContinentName\":\"亚洲\",\"RegionID\":\"1\",\"RegionName\":\"中国大陆\",\"IsApplicative\":1}]},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":true,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"12468154\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"F\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"0\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":227395015,\"RoomName\":\"大床房\",\"PayType\":\"PP\",\"RoomQuantity\":6,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":false,\"IsShowAgencyTag\":true,\"InvoiceType\":2,\"InvoiceMode\":\"1\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"IsNeedCustomerTelephone\":\"T\",\"ExtraBedCurrency\":\"RMB\"},{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[],\"Applicability\":\"10000000\",\"OtherDescription\":\"\"},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":true,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"16223055\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"F\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"0\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"},{\"Code\":\"Promotion\",\"Name\":\"HourlyRoom\",\"Value\":\"7\",\"Desc\":\"钟点房\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[{\"MinMaxType\":\"Min\",\"Time\":3,\"TimeUnit\":\"Hour\",\"MustBeMultiple\":false}],\"TimeLimitInfo\":[{\"DateRestrictions\":[{\"Scope\":\"Arrival\",\"DateType\":\"Time\",\"Start\":\"10:00\",\"End\":\"22:00\"}]}]}],\"Descriptions\":[{\"Text\":\"\",\"Category\":\"Promotion\"},{\"Text\":\"\",\"Category\":\"Promotion\"}],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":251209462,\"RoomName\":\"大床房(钟点房)\",\"PayType\":\"PP\",\"RoomQuantity\":6,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":true,\"IsFromAPI\":false,\"IsShowAgencyTag\":true,\"InvoiceType\":2,\"InvoiceMode\":\"1\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"IsNeedCustomerTelephone\":\"T\",\"ExtraBedCurrency\":\"RMB\"},{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[],\"Applicability\":\"10000000\",\"OtherDescription\":\"\"},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":true,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"17415932\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"F\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"0\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":843158409,\"RoomName\":\"大床房(促销一口价)\",\"PayType\":\"PP\",\"RoomQuantity\":6,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":false,\"IsShowAgencyTag\":true,\"InvoiceType\":2,\"InvoiceMode\":\"1\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"IsNeedCustomerTelephone\":\"T\",\"ExtraBedCurrency\":\"RMB\"},{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":false,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"3307929\"},{\"Code\":\"SupplierID\",\"Name\":\"SupplierID\",\"Value\":\"4768\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"T\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"332\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":505844482,\"RoomName\":\"大床房\",\"PayType\":\"PP\",\"RoomQuantity\":6,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":true,\"IsShowAgencyTag\":true,\"InvoiceType\":3,\"InvoiceMode\":\"0\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"ExtraBedCurrency\":\"RMB\"}]},{\"RoomTypeInfo\":{\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"ChildLimit\":{\"MaxOccupancy\":1,\"MaxAge\":0,\"MinAge\":0},\"Facilities\":[{\"FacilityItem\":[{\"ID\":\"180\",\"Name\":\"有线频道\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"183\",\"Name\":\"电视机\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"264\",\"Name\":\"客房WIFI\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"215\",\"Name\":\"220V电压插座\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"107\",\"Name\":\"空调\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"208\",\"Name\":\"衣柜/衣橱\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"79\",\"Name\":\"24小时热水\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"},{\"FacilityItem\":[{\"ID\":\"93\",\"Name\":\"吹风机\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"}],\"Pictures\":[{\"Type\":\"6\",\"Caption\":\"标准间\",\"URL\":\"http://dimg04.c-ctrip.com/images//200j1f000001gr5ll7FB1_R_550_412.jpg\"},{\"Type\":\"6\",\"Caption\":\"标准间\",\"URL\":\"http://dimg04.c-ctrip.com/images//28030w000000kg5wf23E7_R_550_412.jpg\"}],\"Descriptions\":[],\"RoomBedInfos\":[{\"BedInfo\":[{\"ID\":\"370\",\"Name\":\"单人床\",\"NumberOfBeds\":\"2\",\"BedWidth\":\"1.0\"}],\"ID\":\"361\",\"Name\":\"双床\"}],\"RoomTypeTags\":[],\"RoomTypeID\":95383002,\"RoomTypeName\":\"标准间\",\"StandardRoomType\":\"标准间\",\"RoomQuantity\":8,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"BathRoomType\":\"Unknown\",\"ExtraBedCurrency\":\"RMB\"},\"RoomInfos\":[{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"AreaApplicabilityInfo\":{\"Details\":[{\"ContinentID\":\"1\",\"ContinentName\":\"亚洲\",\"RegionID\":\"1\",\"RegionName\":\"中国大陆\",\"IsApplicative\":1}]},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":true,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"12468154\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"F\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"0\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":227394948,\"RoomName\":\"标准间\",\"PayType\":\"PP\",\"RoomQuantity\":8,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":false,\"IsShowAgencyTag\":true,\"InvoiceType\":2,\"InvoiceMode\":\"1\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"IsNeedCustomerTelephone\":\"T\",\"ExtraBedCurrency\":\"RMB\"},{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":false,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"1912389\"},{\"Code\":\"SupplierID\",\"Name\":\"SupplierID\",\"Value\":\"4768\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"T\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"332\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":505844477,\"RoomName\":\"标准间\",\"PayType\":\"PP\",\"RoomQuantity\":8,\"MaxOccupancy\":2,\"AreaRange\":\"12\",\"FloorRange\":\"1-2\",\"HasWindow\":\"1\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":true,\"IsShowAgencyTag\":true,\"InvoiceType\":3,\"InvoiceMode\":\"0\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"ExtraBedCurrency\":\"RMB\"}]},{\"RoomTypeInfo\":{\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"ChildLimit\":{\"MaxOccupancy\":1,\"MaxAge\":0,\"MinAge\":0},\"Facilities\":[{\"FacilityItem\":[{\"ID\":\"180\",\"Name\":\"有线频道\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"183\",\"Name\":\"电视机\",\"Status\":\"1\"}],\"CategoryName\":\"媒体科技\"},{\"FacilityItem\":[{\"ID\":\"264\",\"Name\":\"客房WIFI\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"215\",\"Name\":\"220V电压插座\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"107\",\"Name\":\"空调\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"208\",\"Name\":\"衣柜/衣橱\",\"Status\":\"1\"}],\"CategoryName\":\"便利设施\"},{\"FacilityItem\":[{\"ID\":\"79\",\"Name\":\"24小时热水\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"},{\"FacilityItem\":[{\"ID\":\"93\",\"Name\":\"吹风机\",\"Status\":\"1\"}],\"CategoryName\":\"浴室\"}],\"Pictures\":[{\"Type\":\"6\",\"Caption\":\"三人间\",\"URL\":\"http://dimg04.c-ctrip.com/images//20031f000001gtd8o5EE5_R_550_412.jpg\"},{\"Type\":\"6\",\"Caption\":\"三人间\",\"URL\":\"http://dimg04.c-ctrip.com/images//280f0w000000kege3CE4C_R_550_412.jpg\"}],\"Descriptions\":[],\"RoomBedInfos\":[{\"BedInfo\":[{\"ID\":\"370\",\"Name\":\"单人床\",\"NumberOfBeds\":\"3\",\"BedWidth\":\"1.0\"}],\"ID\":\"363\",\"Name\":\"多张床\"}],\"RoomTypeTags\":[],\"RoomTypeID\":95383003,\"RoomTypeName\":\"三人间\",\"StandardRoomType\":\"标准间\",\"RoomQuantity\":1,\"MaxOccupancy\":3,\"AreaRange\":\"15\",\"FloorRange\":\"2\",\"HasWindow\":\"2\",\"ExtraBedFee\":\"\",\"BathRoomType\":\"Unknown\",\"ExtraBedCurrency\":\"RMB\"},\"RoomInfos\":[{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"AreaApplicabilityInfo\":{\"Details\":[{\"ContinentID\":\"1\",\"ContinentName\":\"亚洲\",\"RegionID\":\"1\",\"RegionName\":\"中国大陆\",\"IsApplicative\":1}]},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":true,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"986794\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"F\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"0\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":225965361,\"RoomName\":\"三人间\",\"PayType\":\"PP\",\"RoomQuantity\":1,\"MaxOccupancy\":3,\"AreaRange\":\"15\",\"FloorRange\":\"2\",\"HasWindow\":\"2\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":false,\"IsShowAgencyTag\":true,\"InvoiceType\":2,\"InvoiceMode\":\"1\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"IsNeedCustomerTelephone\":\"T\",\"ExtraBedCurrency\":\"RMB\"},{\"ApplicabilityInfo\":{\"ApplicabilityIDs\":[1],\"Applicability\":\"00100000\",\"OtherDescription\":\"\"},\"Smoking\":{\"IsAllowSmoking\":\"2\"},\"BroadNet\":{\"HasBroadnet\":1,\"HasWirelessBroadnet\":\"T\",\"WirelessBroadnetRoom\":\"1\",\"WirelessBroadnetFee\":\"0\",\"HasWiredBroadnet\":\"F\",\"WiredBroadnetRoom\":\"1\",\"WiredBroadnetFee\":\"0\",\"BroadnetFeeDetail\":\"\",\"WirelessBroadnet\":2,\"WiredBroadnet\":0},\"RoomFGToPPInfo\":{\"CanFGToPP\":true},\"RoomGiftInfos\":[],\"ChannelLimit\":{\"IsApp\":true,\"IsWeb\":false,\"IsWeChat\":true},\"ExpressCheckout\":{\"IsSupported\":false},\"RoomTags\":[{\"Code\":\"RateCodeID\",\"Name\":\"RateCodeID\",\"Value\":\"12468419\"},{\"Code\":\"SupplierID\",\"Name\":\"SupplierID\",\"Value\":\"4768\"},{\"Code\":\"IsAgent\",\"Name\":\"IsAgent\",\"Value\":\"T\"},{\"Code\":\"VendorID\",\"Name\":\"VendorID\",\"Value\":\"332\"},{\"Code\":\"GenderType\",\"Name\":\"GenderType\",\"Value\":\"1\"}],\"BookingRules\":[{\"BookingOffsets\":[],\"LengthsOfStay\":[],\"TimeLimitInfo\":[]}],\"Descriptions\":[],\"RoomPromotions\":[],\"MaskCampaignInfos\":[],\"RoomID\":538606619,\"RoomName\":\"三人间\",\"PayType\":\"PP\",\"RoomQuantity\":1,\"MaxOccupancy\":3,\"AreaRange\":\"15\",\"FloorRange\":\"2\",\"HasWindow\":\"2\",\"ExtraBedFee\":\"\",\"IsHourlyRoom\":false,\"IsFromAPI\":true,\"IsShowAgencyTag\":true,\"InvoiceType\":3,\"InvoiceMode\":\"0\",\"IsSupportSpecialInvoice\":\"false\",\"ReceiveTextRemark\":false,\"ExtraBedCurrency\":\"RMB\"}]}],\"LogInfo\":{\"LogID\":\"5541515019028697904\"}}\n";
        List<SubRoomDetail> subRoomStaticBean = getSubRoomStaticBean(result, "258136");
        for (SubRoomDetail subRoomDetail : subRoomStaticBean) {
            String string = JSON.toJSONString(subRoomDetail);
            System.err.println(string);
        }
    }

    public static String getResponseStatus(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取状态码
        JSONObject responseStatus = JSONObject.parseObject(obj.getString("ResponseStatus"));
        String ack = responseStatus.getString("Ack");
        return ack;
    }


    public static String getLastRecordID(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取LastRecordID
        JSONObject pagingInfo = JSONObject.parseObject(obj.getString("PagingInfo"));
        String lastRecordID = pagingInfo.getString("LastRecordID");
        return lastRecordID;
    }

    //全量城市
    public static ArrayList<Destination> getDestinationBean(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        ArrayList<Destination> destinationList = new ArrayList<>();

        JSONObject cityInfos = JSONObject.parseObject(obj.getString("CityInfos"));
        JSONArray cityInfo = cityInfos.getJSONArray("CityInfo");
        for (Object city : cityInfo) {
            Destination destination = new Destination();
            //内层数据
            Coordinates coordinates = new Coordinates();
            String bean = city.toString();
            //jsonBean->CityInfo
            JSONObject jsonBean = JSONObject.parseObject(bean);
            //cds->Coordinates
            JSONArray cdsList = jsonBean.getJSONArray("Coordinates");
            for (Object cds : cdsList) {
                JSONObject cdsJson = JSONObject.parseObject(cds.toString());
                //Coordinates实体数据添加
                coordinates.setProvider(cdsJson.getString("Provider"));
                coordinates.setLAT(cdsJson.getString("LNG"));
                coordinates.setLNG(cdsJson.getString("LAT"));
            }

            //将Coordinates实体添加至Destination实体
            ArrayList<Coordinates> list = new ArrayList<>();
            list.add(coordinates);
            destination.setCoordinates(list);
            destination.setCityID(jsonBean.getString("CityID"));
            destination.setCityName(jsonBean.getString("CityName"));
            destination.setCityEnName(jsonBean.getString("CityEnName"));
            destination.setParentCityID(jsonBean.getString("ParentCityID"));
            destination.setParentCityName(jsonBean.getString("ParentCityName"));
            destination.setParentCityEnName(jsonBean.getString("ParentCityEnName"));
            destination.setProvinceID(jsonBean.getString("ProvinceID"));
            destination.setProvinceName(jsonBean.getString("ProvinceName"));
            destination.setProvinceEnName(jsonBean.getString("ProvinceEnName"));
            destination.setCountryID(jsonBean.getString("CountryID"));
            destination.setCountryName(jsonBean.getString("CountryName"));
            destination.setCountryEnName(jsonBean.getString("CountryEnName"));
            destination.setContinentID(jsonBean.getString("ContinentID"));
            destination.setContinentName(jsonBean.getString("ContinentName"));
            destination.setContinentEnName(jsonBean.getString("ContinentEnName"));
            destinationList.add(destination);
        }
        return destinationList;
    }

    //城市酒店清单
    public static ArrayList<HotelIdDetail> getHotelIdDetailBean(String result, JSONObject cityObj) {
        JSONObject jsonBean = JSONObject.parseObject(result);
        ArrayList<HotelIdDetail> hotelIdDetailList = new ArrayList<>();
        List<String> hotelIDs = (List) jsonBean.get("HotelIDs");
        for (String hotelID : hotelIDs) {
            HotelIdDetail hotelBean = new HotelIdDetail();
            hotelBean.setHotelId(hotelID);
            hotelBean.setCityID(cityObj.getString("CityID"));
            hotelBean.setCityName(cityObj.getString("CityName"));
            hotelBean.setCityName(cityObj.getString("CityName"));
            hotelBean.setProvinceID(cityObj.getString("ProvinceID"));
            hotelBean.setProvinceName(cityObj.getString("ProvinceName"));
            hotelIdDetailList.add(hotelBean);
        }
        return hotelIdDetailList;
    }

    //酒店静态信息
    public static HotelDetail getHotelIdDetailBean(String result) {
        JSONObject hotelStaticInfo = JSONObject.parseObject(result).getJSONObject("HotelStaticInfo");
        String hotelNameEN = "";
        String hotelAddressEN = "";
        String masterHotelNum = hotelStaticInfo.getString("HotelID");
        String hotelName = hotelStaticInfo.getString("HotelName");
        JSONArray hotelTags = hotelStaticInfo.getJSONArray("HotelTags");
        //酒店和地址英文名称
        for (Object hotelTag : hotelTags) {
            JSONObject hotelTagsJson = JSONObject.parseObject(hotelTag.toString());
            if ("HotelNameEN".equals(hotelTagsJson.getString("Name"))) {
                //new 对象 到时候直接set
                hotelNameEN = hotelTagsJson.getString("Value");
            } else if ("HotelAddressEN".equals(hotelTagsJson.getString("Name"))) {
                hotelAddressEN = hotelTagsJson.getString("Value");
            }
        }
        Integer starRating = hotelStaticInfo.getInteger("StarRating"); //星级
        Boolean isOfficialRating = hotelStaticInfo.getBoolean("IsOfficialRating");//标明星级是否有政府机构评定
        String openYear = hotelStaticInfo.getString("OpenYear");
        Integer roomQuantity = hotelStaticInfo.getInteger("RoomQuantity");//酒店的客房数量
        String telephone = hotelStaticInfo.getJSONObject("ContactInfo").getString("Telephone");//电话
        String fax = hotelStaticInfo.getJSONObject("ContactInfo").getString("Fax");//传真
        //GeoInfo
        JSONObject geoInfo = hotelStaticInfo.getJSONObject("GeoInfo");
        String address = geoInfo.getString("Address");//地址
        String postalCode = geoInfo.getString("PostalCode");//酒店所在的城市区域代码
        String country = geoInfo.getJSONObject("Country").getString("Name");//国家
        String province = geoInfo.getJSONObject("Province").getString("Name");//省份
        String city = geoInfo.getJSONObject("City").getString("Name");//城市

        ArrayList<Coordinates> coordinatesWays = new ArrayList<>();
        JSONArray coordinates = geoInfo.getJSONArray("Coordinates");
        for (Object coordinate : coordinates) {
            Coordinates cds = new Coordinates();
            String provider = JSONObject.parseObject(coordinate.toString()).getString("Provider");//经纬度提供者
            String lng = JSONObject.parseObject(coordinate.toString()).getString("LNG");//经纬
            String lat = JSONObject.parseObject(coordinate.toString()).getString("LAT");//经纬
            cds.setProvider(provider);
            cds.setLNG(lng);
            cds.setLAT(lat);
            coordinatesWays.add(cds);
        }

        HotelDetail hotelDetailBean = new HotelDetail();
        //set
        hotelDetailBean.setUpdateTimeStamp(System.currentTimeMillis());
        hotelDetailBean.setMasterHotelNum(masterHotelNum);
        hotelDetailBean.setDataFlag(1);
        hotelDetailBean.setHotelName(hotelName);
        if (!"".equals(hotelNameEN)) {
            hotelDetailBean.setHotelNameEn(hotelNameEN);
        }
        if (!"".equals(hotelAddressEN)) {
            hotelDetailBean.setAddressEn(hotelAddressEN);
        }
        hotelDetailBean.setStarRating(starRating);
        hotelDetailBean.setIsOfficialRating(isOfficialRating);
        hotelDetailBean.setOpenYear(openYear);
        hotelDetailBean.setRoomQuantity(roomQuantity);
        hotelDetailBean.setAddress(address);
        hotelDetailBean.setCoordinates(coordinatesWays);
        hotelDetailBean.setCountryName(country);
        hotelDetailBean.setProvinceName(province);
        hotelDetailBean.setCityName(city);
        hotelDetailBean.setPostalCode(postalCode);
        return hotelDetailBean;
    }

    //房型静态信息(物理房型)
    public static List<RoomDetail> getRoomStaticBean(String result, String masterHotelNum) {

        ArrayList<RoomDetail> list = new ArrayList<>();

        JSONArray roomStaticInfos = JSONObject.parseObject(result).getJSONArray("RoomStaticInfos");
        for (Object roomStaticInfo : roomStaticInfos) {
            //先解析RoomTypeInfo
            String roomTypeInfo = JSONObject.parseObject(roomStaticInfo.toString()).getString("RoomTypeInfo");
            JSONObject jsonRoomType = JSONObject.parseObject(roomTypeInfo);
            String roomId = jsonRoomType.getString("RoomTypeID");
            String roomTypeName = jsonRoomType.getString("RoomTypeName");
            //RoomTypeName_En
            String standardRoomType = jsonRoomType.getString("StandardRoomType");
            Integer roomQuantity = jsonRoomType.getInteger("RoomQuantity");
            Integer maxOccupancy = jsonRoomType.getInteger("MaxOccupancy");
            String areaRange = jsonRoomType.getString("AreaRange");
            String floorRange = jsonRoomType.getString("FloorRange");
            Integer hasWindow = jsonRoomType.getInteger("HasWindow");
            String bathRoomType = jsonRoomType.getString("BathRoomType");
            //MaxChild
            String maxChild = jsonRoomType.getJSONObject("ChildLimit").getString("MaxOccupancy");
            //Beds
            JSONArray beaArray = jsonRoomType.getJSONArray("RoomBedInfos");
            //床的信息
            ArrayList<String> bedList = new ArrayList<>();
            for (Object bea : beaArray) {
                String bedType = "";
                JSONObject bed = JSONObject.parseObject(bea.toString());
                //当床型分类ID为多床（ID=363）时，BedInfo数组中多个床型之间的关系为“且”；当床型分类ID非多床（ID!=363）时，BedInfo数组中多个床型之间的关系为“或”。
                String logo = "|";
                String badId = bed.getString("ID");
                if ("363".equals(badId)) {
                    logo = "&";
                }
                JSONArray bedInfoJson = bed.getJSONArray("BedInfo");
                for (Object bedInfo : bedInfoJson) {
                    bedType = JSONObject.parseObject(bedInfo.toString()).getString("Name") + logo;
                }
                bedList.add(bedType);
            }
            RoomDetail roomDetail = new RoomDetail();
            roomDetail.setUpdateTimeStamp(System.currentTimeMillis());
            roomDetail.setMasterHotelNum(masterHotelNum);
            roomDetail.setRoomId(roomId);
            roomDetail.setRoomTypeName(roomTypeName);
            roomDetail.setStandardRoomType(standardRoomType);
            roomDetail.setRoomQuantity(roomQuantity);
            roomDetail.setMaxOccupancy(maxOccupancy);
            roomDetail.setMaxChild(maxChild);
            roomDetail.setAreaRange(areaRange);
            roomDetail.setFloorRange(floorRange);
            roomDetail.setHasWindow(hasWindow);
            roomDetail.setBathRoomType(bathRoomType);
            roomDetail.setBeds(bedList);
            list.add(roomDetail);
        }
        return list;
    }

    //房型静态信息(子房型)
    public static List<SubRoomDetail> getSubRoomStaticBean(String result, String masterHotelNum) {

        JSONArray roomStaticInfos = JSONObject.parseObject(result).getJSONArray("RoomStaticInfos");
        ArrayList<SubRoomDetail> subList = new ArrayList<>();
        for (Object roomStaticInfo : roomStaticInfos) {
            //获取物理房型的id
            String roomTypeInfo = JSONObject.parseObject(roomStaticInfo.toString()).getString("RoomTypeInfo");
            JSONObject jsonRoomType = JSONObject.parseObject(roomTypeInfo);
            String roomId = jsonRoomType.getString("RoomTypeID");
            //解析RoomInfos
            JSONArray roomInfos = JSONObject.parseObject(roomStaticInfo.toString()).getJSONArray("RoomInfos");
            for (Object roomInfo : roomInfos) {
                JSONObject subRoomJson = JSONObject.parseObject(roomInfo.toString());
                String subRoomId = subRoomJson.getString("RoomID");
                String roomName = subRoomJson.getString("RoomName");
                String payType = subRoomJson.getString("PayType");
                Integer roomQuantity = subRoomJson.getInteger("RoomQuantity");
                Integer maxOccupancy = subRoomJson.getInteger("MaxOccupancy");
                String areaRange = subRoomJson.getString("AreaRange");
                String floorRange = subRoomJson.getString("FloorRange");
                Integer hasWindow = subRoomJson.getInteger("HasWindow");
                String extraBedFee = subRoomJson.getString("ExtraBedFee");
                Boolean isHourlyRoom = subRoomJson.getBoolean("IsHourlyRoom");
                Boolean isFromAPI = subRoomJson.getBoolean("IsFromAPI");
                Boolean isShowAgencyTag = subRoomJson.getBoolean("IsShowAgencyTag");
                Integer invoiceType = subRoomJson.getInteger("InvoiceType");
                String invoiceMode = subRoomJson.getString("InvoiceMode");
                String isSupportSpecialInvoice = subRoomJson.getString("IsSupportSpecialInvoice");
                Boolean receiveTextRemark = subRoomJson.getBoolean("ReceiveTextRemark");
                String isNeedCustomerTelephone = subRoomJson.getString("IsNeedCustomerTelephone");
                String isAllowRepricing = subRoomJson.getString("IsAllowRepricing");
                String isClosed = subRoomJson.getString("IsClosed");
                String isAllowSmoking = JSONObject.parseObject(subRoomJson.getString("Smoking")).getString("IsAllowSmoking");

                //添加实体属性
                SubRoomDetail subRoomDetail = new SubRoomDetail();
                subRoomDetail.setUpdateTimeStamp(System.currentTimeMillis());
                subRoomDetail.setMasterHotelNum(masterHotelNum);
                subRoomDetail.setRoomCode(subRoomId);
                subRoomDetail.setRoomId(roomId);
                subRoomDetail.setRoomName(roomName);
                subRoomDetail.setPayType(payType);
                subRoomDetail.setRoomQuantity(roomQuantity);
                subRoomDetail.setMaxOccupancy(maxOccupancy);
                subRoomDetail.setAreaRange(areaRange);
                subRoomDetail.setFloorRange(floorRange);
                subRoomDetail.setHasWindow(hasWindow);
                subRoomDetail.setExtraBedFee(extraBedFee);
                subRoomDetail.setIsHourlyRoom(isHourlyRoom ? "true" : "false");
                subRoomDetail.setIsFromAPI(isFromAPI);
                subRoomDetail.setIsShowAgencyTag(isShowAgencyTag);
                subRoomDetail.setInvoiceType(invoiceType);
                subRoomDetail.setInvoiceMode(invoiceMode);
                subRoomDetail.setIsSupportSpecialInvoice(isSupportSpecialInvoice);
                subRoomDetail.setReceiveTextRemark(receiveTextRemark);
                subRoomDetail.setIsNeedCustomerTelephone(isNeedCustomerTelephone);
                subRoomDetail.setIsClosed(isClosed);
                subRoomDetail.setIsAllowRepricing(isAllowRepricing);
                subRoomDetail.setIsAllowSmoking(isAllowSmoking);

                //BookingRules
                JSONArray bookingRules = subRoomJson.getJSONArray("BookingRules");
                ArrayList<TimeLimitInfo> tliList = new ArrayList<>();
                for (Object bookingRule : bookingRules) {
                    JSONArray timeLimitInfos = JSONObject.parseObject(bookingRule.toString()).getJSONArray("TimeLimitInfo");
                    //第二层list 放TimeLimitInfo
                    TimeLimitInfo tli = new TimeLimitInfo();
                    for (Object timeLimitInfo : timeLimitInfos) {
                        //第一层list 放DateRestriction
                        ArrayList<DateRestriction> drcList = new ArrayList<>();
                        JSONArray dateRestrictions = JSONObject.parseObject(timeLimitInfo.toString()).getJSONArray("DateRestrictions");
                        for (Object dateRestriction : dateRestrictions) {
                            DateRestriction drc = new DateRestriction();
                            String scope = JSONObject.parseObject(dateRestriction.toString()).getString("Scope");
                            String dateType = JSONObject.parseObject(dateRestriction.toString()).getString("DateType");
                            String start = JSONObject.parseObject(dateRestriction.toString()).getString("Start");
                            String end = JSONObject.parseObject(dateRestriction.toString()).getString("End");
                            drc.setDataType(dateType);
                            drc.setScope(scope);
                            drc.setStart(start);
                            drc.setEnd(end);
                            drcList.add(drc);
                        }
                        tli.setDateRestrictions(drcList);
                    }
                    tliList.add(tli);
                }
                subRoomDetail.setTimeLimitInfos(tliList);
                subList.add(subRoomDetail);
            }
        }
        return subList;
    }


}
