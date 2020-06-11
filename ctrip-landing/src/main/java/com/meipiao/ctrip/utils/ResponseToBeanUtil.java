package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.Coordinates;
import com.meipiao.ctrip.entity.response.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;

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
        String result = "{\"ResponseStatus\":{\"Timestamp\":\"2020-06-11T09:17:22.720+08:00\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[]},\"HotelStaticInfo\":{\"GeoInfo\":{\"City\":{\"Code\":\"1\",\"Name\":\"北京\"},\"Area\":{\"Code\":\"94\",\"Name\":\"朝阳区\"},\"PostalCode\":\"\",\"Address\":\"百子湾西里104号2楼\",\"BusinessDistrict\":[{\"Code\":\"767\",\"Name\":\"国贸地区\"}],\"Coordinates\":[{\"Provider\":\"Baidu\",\"LNG\":116.50947003046,\"LAT\":39.90663946452},{\"Provider\":\"Google\",\"LNG\":116.503046,\"LAT\":39.900419},{\"Provider\":\"AutoNavi\",\"LNG\":116.503046,\"LAT\":39.900419}],\"Province\":{\"Code\":\"1\",\"Name\":\"北京\"},\"Country\":{\"Code\":\"1\",\"Name\":\"中国\"}},\"ContactInfo\":{\"Telephone\":\"010-87725022\",\"Fax\":\"010-87725022\"},\"HotelTags\":[{\"Code\":\"ReservedData\",\"Name\":\"HotelNameEN\",\"Value\":\"Pin'ai Theme Hotel\"},{\"Code\":\"ReservedData\",\"Name\":\"HotelAddressEN\",\"Value\":\"Baiziwan (West Lane)\"}],\"ApplicabilityInfo\":{\"HotelApplicability\":\"3\"},\"HotelID\":4998653,\"HotelName\":\"北京品爱精品酒店\",\"StarRating\":2,\"IsOfficialRating\":false,\"OpenYear\":\"2016-12-01\",\"RenovationYear\":\"2017-04-01\",\"RoomQuantity\":32,\"IsOnlineSignUp\":true},\"LogInfo\":{\"LogID\":\"3862620825121468936\"}}\n";
        HotelDetail hotelIdDetailBean = getHotelIdDetailBean(result);
        System.out.println(hotelIdDetailBean.toString());

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
        return  hotelDetailBean;
    }
}
