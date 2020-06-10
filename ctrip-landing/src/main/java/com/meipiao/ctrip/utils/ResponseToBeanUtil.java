package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.Coordinates;
import com.meipiao.ctrip.entity.response.Destination;
import com.meipiao.ctrip.entity.response.HotelIdDetail;

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
        String result = "{\"ResponseStatus\":{\"Timestamp\":\"2020-06-10T09:45:11.164+08:00\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[]},\"PagingInfo\":{\"LastRecordID\":\"1A1000003292\"},\"CityInfos\":{\"CityInfo\":[{\"Coordinates\":[{\"Provider\":\"BD\",\"LNG\":116.4137840210,\"LAT\":39.9105329229}],\"CityID\":\"1\",\"CityName\":\"北京\",\"CityEnName\":\"Beijing\",\"ParentCityID\":\"\",\"ParentCityName\":\"\",\"ParentCityEnName\":\"\",\"ProvinceID\":\"1\",\"ProvinceName\":\"北京\",\"ProvinceEnName\":\"Beijing\",\"CountryID\":\"1\",\"CountryName\":\"中国\",\"CountryEnName\":\"China\",\"ContinentID\":\"1\",\"ContinentName\":\"亚洲\",\"ContinentEnName\":\"Asia\"},{\"Coordinates\":[{\"Provider\":\"BD\",\"LNG\":121.4802384079,\"LAT\":31.2363508011}],\"CityID\":\"2\",\"CityName\":\"上海\",\"CityEnName\":\"Shanghai\",\"ParentCityID\":\"\",\"ParentCityName\":\"\",\"ParentCityEnName\":\"\",\"ProvinceID\":\"2\",\"ProvinceName\":\"上海\",\"ProvinceEnName\":\"Shanghai\",\"CountryID\":\"1\",\"CountryName\":\"中国\",\"CountryEnName\":\"China\",\"ContinentID\":\"1\",\"ContinentName\":\"亚洲\",\"ContinentEnName\":\"Asia\"}]}}\n";
        ArrayList<Destination> destinationEntity = getDestinationEntity(result);
        for (Destination destination1 : destinationEntity) {
            System.err.println(destination1);
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
    public static ArrayList<Destination> getDestinationEntity(String result) {
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
    public static ArrayList<HotelIdDetail> getHotelIdDetail(String result,JSONObject cityObj) {
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
}
