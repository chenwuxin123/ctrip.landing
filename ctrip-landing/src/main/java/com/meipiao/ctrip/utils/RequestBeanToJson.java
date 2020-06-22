package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSON;
import com.meipiao.ctrip.entity.request.city.GetCityEntityReq;
import com.meipiao.ctrip.entity.request.city.SearchByType;
import com.meipiao.ctrip.entity.request.city.SearchCandidate;
import com.meipiao.ctrip.entity.request.hotel.*;
import com.meipiao.ctrip.entity.request.inctement.GetPriceEntityReq;
import com.meipiao.ctrip.entity.request.inctement.IncrPriceSearchCandidate;
import com.meipiao.ctrip.entity.request.inctement.IncrPriceSettings;
import com.meipiao.ctrip.entity.request.page.PagingSettings;
import com.meipiao.ctrip.entity.request.rate.*;
import com.meipiao.ctrip.entity.request.room.GetRoomStaticReq;
import com.meipiao.ctrip.entity.request.room.RoomSearchCandidate;
import com.meipiao.ctrip.entity.request.room.RoomSettings;
import com.meipiao.ctrip.entity.request.room.SearchTagsItem;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 实体转json
 *
 * @Author: Chenwx
 * @Date: 2020/6/12 11:02
 */
public class RequestBeanToJson {

    public static void main(String[] args) {


    }

    //获取全量城市信息请求参数(json)
    public static String getCityEntityReq(Integer pageSize, String lastRecordID) {
        GetCityEntityReq getCityEntityReq = new GetCityEntityReq();

        SearchCandidate searchCandidate = new SearchCandidate();
        SearchByType searchByType = new SearchByType();
        searchByType.setIsHaveHotel("F");//搜索范围描述:1.中国(包含港澳台地区) 2.海外 3.全部
        searchByType.setSearchType("1");//是否输出有酒店的城市
        searchCandidate.setSearchByType(searchByType);

        //PagingSettings
        PagingSettings pagingSettings = new PagingSettings();
        pagingSettings.setLastRecordID(lastRecordID);//酒店ID
        pagingSettings.setPageSize(pageSize);//分页每次请求售卖房型数量，结算价分销商请求该接口时若接口返回房型数量超过200时，接口默认返回200个房型
        getCityEntityReq.setPagingSettings(pagingSettings);
        getCityEntityReq.setSearchCandidate(searchCandidate);

        String json = JSON.toJSONString(getCityEntityReq);
        return json;
    }

    //获取酒店清单请求参数(json)
    public static String getCityHotelIdReq(String cityId, Integer pageSize, String lastRecordID) {
        GetCityHotelIdReq getCityHotelIdReq = new GetCityHotelIdReq();
        //PagingSettings
        PagingSettings pagingSettings = new PagingSettings();
        pagingSettings.setLastRecordID(lastRecordID);
        pagingSettings.setPageSize(pageSize);
        getCityHotelIdReq.setPagingSettings(pagingSettings);

        HSearchCandidate hsearchCandidate = new HSearchCandidate();
        SearchByCityID searchByCityID = new SearchByCityID();
        searchByCityID.setCityID(cityId);//酒店ID
        hsearchCandidate.setSearchByCityID(searchByCityID);
        getCityHotelIdReq.setSearchCandidate(hsearchCandidate);
        String json = JSON.toJSONString(getCityHotelIdReq);
        return json;
    }

    //获取酒店静态信息请求参数
    public static String getHotelStaticReq(String hotelId) {
        GetHotelStaticReq getHotelStaticReq = new GetHotelStaticReq();
        //SearchCandidate
        CtripHotelId ctripHotelId = new CtripHotelId();
        ctripHotelId.setHotelID(hotelId);
        getHotelStaticReq.setSearchCandidate(ctripHotelId);

        //Settings
        Settings settings = new Settings();
        getHotelStaticReq.setSettings(settings);
        String json = JSON.toJSONString(getHotelStaticReq);
        return json;
    }

    //获取房型静态信息请求参数
    public static String getRoomStaticReq(String hotelId, Integer pageSize, String lastRecordID) {
        GetRoomStaticReq getRoomStaticReq = new GetRoomStaticReq();
        //SearchCandidate
        RoomSearchCandidate roomSearchCandidate = new RoomSearchCandidate();
        roomSearchCandidate.setHotelID(hotelId);
        getRoomStaticReq.setSearchCandidate(roomSearchCandidate);

        //Settings
        RoomSettings roomSettings = new RoomSettings();
        //SearchTags
        ArrayList<SearchTagsItem> list = new ArrayList<>();
        SearchTagsItem searchTagsItemF = new SearchTagsItem();
        SearchTagsItem searchTagsItemS = new SearchTagsItem();
        searchTagsItemF.setCode("IsOutputHiddenMaskRoom");
        searchTagsItemS.setCode("IsOutputLimitDestinationRoom");
        list.add(searchTagsItemF);
        list.add(searchTagsItemS);
        roomSettings.setSearchTags(list);
        getRoomStaticReq.setSettings(roomSettings);

        //PagingSettings
        PagingSettings pagingSettings = new PagingSettings();
        pagingSettings.setLastRecordID(lastRecordID);//酒店ID
        pagingSettings.setPageSize(pageSize);//分页每次请求售卖房型数量，结算价分销商请求该接口时若接口返回房型数量超过200时，接口默认返回200个房型
        getRoomStaticReq.setPagingSettings(pagingSettings);
        String json = JSON.toJSONString(getRoomStaticReq);
        return json;
    }

    //直连价格请求参数(json)
    public static String getRateEntityReq(String hotelId, String lastRecordID, Integer pageSize, String start, String end) {
        GetRateEntityReq getRateEntityReq = new GetRateEntityReq();
        //Settings
        RateSettings rateSettings = new RateSettings();
        getRateEntityReq.setSettings(rateSettings);
        //PagingSettings
        PagingSettings pagingSettings = new PagingSettings();
        pagingSettings.setLastRecordID(lastRecordID);//酒店ID
        pagingSettings.setPageSize(pageSize);//分页每次请求售卖房型数量，结算价分销商请求该接口时若接口返回房型数量超过200时，接口默认返回200个房型
        getRateEntityReq.setPagingSettings(pagingSettings);

        //SearchCandidate
        RateSearchCandidate searchCandidate = new RateSearchCandidate();
        //HotelID传参
        searchCandidate.setHotelID(hotelId);

        //SearchCandidate-DateRange
        DateRange dateRange = new DateRange();
        //入离日期
        dateRange.setStart(start);//入住日期
        dateRange.setEnd(end);//离店日期
        searchCandidate.setDateRange(dateRange);

        //SearchCandidate-Occupancy
        Occupancy occupancy = new Occupancy();
        occupancy.setAdult(1);//单间房入住成人数限制
        occupancy.setChild(null);//单间房入住儿童数限制

        //SearchCandidate-Occupancy-ChildInfo
        ArrayList<ChildInfoItem> childList = new ArrayList<>();
        ChildInfoItem childInfoItem = new ChildInfoItem(); //循环 接口list<ChildInfoItem>集合
        childInfoItem.setAge(null);//儿童年龄;若Child数量不为null，则该字段必传
        childInfoItem.setCount(null);//儿童数量;若Child数量不为null，则该字段必传
        childList.add(childInfoItem);
        occupancy.setChildInfo(childList);
        searchCandidate.setOccupancy(occupancy);

        //SearchCandidate-SearchTags
        ArrayList<RateSearchTagsItem> rstList = new ArrayList<>();

        RateSearchTagsItem rateSearchTagsItem = new RateSearchTagsItem();
        rateSearchTagsItem.setCode("MemberLevel");
        rateSearchTagsItem.setName("");
        rateSearchTagsItem.setValue("100");
        RateSearchTagsItem rateSearchTagsItem2 = new RateSearchTagsItem();
        rateSearchTagsItem2.setCode("IsPrepayDiscountMoney");//循环 接口list<RateSearchTagsItem>集合
        rateSearchTagsItem2.setName("");
        rateSearchTagsItem2.setValue("T");
        rstList.add(rateSearchTagsItem);
        rstList.add(rateSearchTagsItem2);
        searchCandidate.setSearchTags(rstList);

        getRateEntityReq.setSearchCandidate(searchCandidate);

        return JSON.toJSONString(getRateEntityReq);
    }

    //监测房价、房量、房态增量(json)
    public static String getIncrPriceEntityReq(String lastRecordID, Integer pageSize, String startTime) {
        GetPriceEntityReq getPriceEntityReq = new GetPriceEntityReq();
        //PagingSettings
        PagingSettings pagingSettings = new PagingSettings();
        pagingSettings.setLastRecordID(lastRecordID);//酒店ID
        pagingSettings.setPageSize(pageSize);//分页每次请求售卖房型数量，结算价分销商请求该接口时若接口返回房型数量超过200时，接口默认返回200个房型
        getPriceEntityReq.setPagingSettings(pagingSettings);

        //Settings
        IncrPriceSettings incrPriceSettings = new IncrPriceSettings();
        incrPriceSettings.setShowDataRange("");//不填写，则返回房价、房量、房态的增量。
        incrPriceSettings.setIsShowChangeDetails("T");
        getPriceEntityReq.setSettings(incrPriceSettings);

        //SearchCandidate
        IncrPriceSearchCandidate incrPriceSearchCandidate = new IncrPriceSearchCandidate();
        incrPriceSearchCandidate.setStartTime(startTime);
        incrPriceSearchCandidate.setDuration(0);
        getPriceEntityReq.setSearchCandidate(incrPriceSearchCandidate);

        return JSON.toJSONString(getPriceEntityReq);
    }
}
