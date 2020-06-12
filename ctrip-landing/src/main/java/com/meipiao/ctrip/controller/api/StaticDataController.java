package com.meipiao.ctrip.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.common.R;
import com.meipiao.ctrip.constant.TokenConstant;
import com.meipiao.ctrip.controller.auth.AuthorityController;
import com.meipiao.ctrip.entity.response.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
import com.meipiao.ctrip.utils.HttpClientUtil;
import com.meipiao.ctrip.utils.MongoAggregationUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import com.meipiao.ctrip.utils.ResponseToBeanUtil;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Chenwx
 * @Date: 2020/6/9 10:16
 */
@RestController
@Slf4j
@SuppressWarnings("unchecked")
@RequestMapping("/api")
@Api(value = "Ctrip Data Landing API", tags = {"携程数据落地API"})
public class StaticDataController {

    @Autowired
    AuthorityController authorityController;

    @Autowired
    MongoTemplate mongoTemplate;

    @Resource
    RedisUtil redisUtil;

    @Autowired
    MongoAggregationUtil mongoAggregationUtil;

    @Value("${CITY.ICODE}")
    private String cityICODE;   //获取全量城市信息ICODE

    @Value("${HOTELID.ICODE}")
    private String hotelidICODE;   //获取酒店清单ICODE

    @Value("${HOTEL.INFO.ICODE}")
    private String hotelInfoICODE;   //获取酒店静态信息ICODE

    @Value("${ROOM.INFO.ICODE}")
    private String roomInfoICODE;   //获取房型静态信息ICODE

    @Value("${RATE.DIRECT}")
    private String rateDirect;   //报价实时查询接口（国内酒店+海外酒店)

    @Value("${ctrip.http.address}")
    private String httpAddress;     //携程请求地址

    @Value("${ctrip.aid}")
    private String aid;             //aid

    @Value("${ctrip.sid}")
    private String sid;             //sid

    @Value("${UniqueID}")          //UniqueID
    private String UniqueID;

    private final String tokenKey = TokenConstant.TOKENKEY; //有效token对应的key


    private Map putParam() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("AID", aid);
        map.put("SID", sid);
        map.put("UUID", UniqueID);
        map.put("mode", "1");
        map.put("format", "json");
        return map;
    }

    //加锁
    private boolean lock(String UUID) {
        //持有1s的锁 setnx并发时只有一个人可以设置到值
        return redisUtil.setnx("lock", UUID, 1);
    }

    //删锁
    private void delLock(String UUID) {
        if (UUID.equals(redisUtil.get("lock"))) {
            redisUtil.del("lock");
        }
    }

    private String getAccessToken(String UUID) throws InterruptedException {
        String access_token = null;
        //获取Access Token
        Map<Object, Object> tokenMap = redisUtil.hmget(tokenKey);
        if (tokenMap.size() > 0) {
            for (Object token : tokenMap.keySet()) {
                access_token = token.toString();
            }
        } else if (lock(UUID)) {
            //刷新token 后续使用redis锁，没有拿到锁的线程进行自旋
            access_token = authorityController.refreshToken();
            delLock(UUID);
        } else {
            Thread.sleep(1000);
            String backUp = java.util.UUID.randomUUID().toString();
            getAccessToken(backUp);
        }
        return access_token;
    }

    @ApiOperation(value = "获取全量城市信息")
    @GetMapping("/city")
    @Async
    /*
          SearchByType:  搜索范围描述 1.中国(包含港澳台地区) 2.海外 3.全部
          IsHaveHotel: 是否输出有酒店的城市
          PageSize: 每页记录数，最大限制5000
          LastRecordID: 首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID
     */
    public R City() throws InterruptedException {
        //获取sid aid uuid 请求的ICODE lock的UUID
        int PageSize = 5000;
        Map map = putParam();
        map.put("ICODE", cityICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        String LastRecordID = "";
        do {
            String SearchType = "1";
            String IsHaveHotel = "F";

            //获得Access Token
            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);
            String json = "{\n" +
                    "    \"SearchCandidate\":{\n" +
                    "        \"SearchByType\":{\n" +
                    "            \"SearchType\":\"" + SearchType + "\",\n" +
                    "            \"IsHaveHotel\":\"" + IsHaveHotel + "\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"PagingSettings\":{\n" +
                    "        \"PageSize\":\"" + PageSize + "\",\n" +
                    "        \"LastRecordID\":\"" + LastRecordID + "\"\n" +
                    "    }\n" +
                    "}";
            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}", Thread.currentThread().getName(), result);
                return R.fail();
            }
            //获取实体集合，添加至mongodb
            ArrayList<Destination> destinationEntity = ResponseToBeanUtil.getDestinationBean(result);
            mongoTemplate.insert(destinationEntity, "Destination");
            LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
        } while (!"".equals(LastRecordID));
        return R.ok();
    }

    @ApiOperation(value = "获取酒店清单")
    @GetMapping("/hotel/id")
    @Async
    /*
         CityID: 城市ID
         PageSize: 每页记录数，最大限制5000
         LastRecordID: 首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID
     */
    public R getHotelID() throws InterruptedException {
        /*
            获取mongodb中所有CityID 进行遍历查询HotelId
        */
        //查找城市id
        int PageSize = 5000;
        //获取sid aid uuid 请求的ICODE lock的UUID
        Map map = putParam();
        map.put("ICODE", hotelidICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        Aggregation aggregation = mongoAggregationUtil.findAllByColumn("CityID", "CityName", "ProvinceID", "ProvinceName");
        AggregationResults<BasicDBObject> cityInfo = mongoTemplate.aggregate(aggregation, "Destination", BasicDBObject.class);
        for (BasicDBObject basicDBObject : cityInfo) {
            String cityJson = basicDBObject.toJson();
            //cityObj是城市信息
            JSONObject cityObj = JSONObject.parseObject(cityJson);
            String cityID = cityObj.getString("CityID");
            //执行业务
            String LastRecordID = "";
            do {
                //获取Access Token
                String accessToken = getAccessToken(UUID);
                map.put("Token", accessToken);
                String json = "{\n" +
                        "    \"SearchCandidate\": {\n" +
                        "        \"SearchByCityID\": {\n" +
                        "            \"CityID\": \"" + cityID + "\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"PagingSettings\": {\n" +
                        "        \"PageSize\":\"" + PageSize + "\",\n" +
                        "        \"LastRecordID\":\"" + LastRecordID + "\"\n" +
                        "    }\n" +
                        "}";
                log.info("请求的json:{}", json);
                String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
                String result = HttpClientUtil.doPostJson(serverHost, map, json);
                String ack = ResponseToBeanUtil.getResponseStatus(result);
                if (!"Success".equals(ack)) {
                    log.warn("{}请求出现错误!错误信息{}", Thread.currentThread().getName(), result);
                    return R.fail();
                }
                //获取实体集合，添加至mongodb
                ArrayList<HotelIdDetail> hotelIdDetail = ResponseToBeanUtil.getHotelIdDetailBean(result, cityObj);
                mongoTemplate.insert(hotelIdDetail, "HotelIdDetail");
                LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
            } while (!"".equals(LastRecordID));
        }
        return R.ok();
    }

    @ApiOperation(value = "获取酒店静态信息")
    @GetMapping("/hotel/static")
    @Async
    public R getHotelStatic() throws InterruptedException {
        //获取sid aid uuid 请求的ICODE lock的UUID
        Map map = putParam();
        map.put("ICODE", hotelInfoICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        //查找酒店id
        Aggregation aggregation = mongoAggregationUtil.findAllByColumn("HotelId");
        AggregationResults<BasicDBObject> hotelIdDetail = mongoTemplate.aggregate(aggregation, "HotelIdDetail", BasicDBObject.class);
        for (BasicDBObject basicDBObject : hotelIdDetail) {
            JSONObject obj = JSONObject.parseObject(basicDBObject.toJson());
            String HotelID = obj.getString("HotelId");
            //获得Access Token

            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);

            String json = "{\n" +
                    "    \"SearchCandidate\":{\n" +
                    "        \"HotelID\":" + HotelID + "\n" +
                    "    },\n" +
                    "    \"Settings\":{\n" +
                    "        \"PrimaryLangID\":\"zh-cn\",\n" +
                    "        \"ExtendedNodes\":[\n" +
                    "            \"HotelStaticInfo.GeoInfo\",\n" +
                    "            \"HotelStaticInfo.ContactInfo\",\n" +
                    "            \"HotelStaticInfo.HotelTags.ReservedData\"\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";

            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}", Thread.currentThread().getName(), result);
                return R.fail();
            }
            //获取实体集合，添加至mongodb
            HotelDetail hotelIdDetailBean = ResponseToBeanUtil.getHotelIdDetailBean(result);
            mongoTemplate.insert(hotelIdDetailBean, "HotelDetail");
        }
        return R.ok();
    }

    @ApiOperation(value = "获取房型静态信息")
    @GetMapping("/room/static")
    @Async
    public R getRoomStatic() throws InterruptedException {

        int PageSize = 1000;
        //获取sid aid uuid 请求的ICODE lock的UUID
        Map map = putParam();
        map.put("ICODE", roomInfoICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        //查找酒店id
        Aggregation aggregation = mongoAggregationUtil.findAllByColumn("HotelId");
        AggregationResults<BasicDBObject> hotelIds = mongoTemplate.aggregate(aggregation, "HotelIdDetail", BasicDBObject.class);

        for (BasicDBObject basicDBObject : hotelIds) {
            String hotelIdJson = basicDBObject.toJson();
            //hotelIdObj是物理房间的id
            JSONObject hotelIdObj = JSONObject.parseObject(hotelIdJson);
            String HotelID = hotelIdObj.getString("HotelId");
            String LastRecordID = "";
            //执行业务
            do {
                //获得Access Token
                String accessToken = getAccessToken(UUID);
                map.put("Token", accessToken);
                String json = "{\n" +
                        "    \"SearchCandidate\":{\n" +
                        "        \"HotelID\":\"" + HotelID + "\",\n" +
                        "        \"RoomIDs\":[]\n" +
                        "    },\n" +
                        "    \"Settings\":{\n" +
                        "        \"PrimaryLangID\":\"zh-cn\",\n" +
                        "        \"ExtendedNodes\":[ \n" +
                        "            \"RoomTypeInfo.Facilities\",\n" +
                        "            \"RoomTypeInfo.Pictures\",\n" +
                        "            \"RoomTypeInfo.Descriptions\",\n" +
                        "            \"RoomTypeInfo.ChildLimit\",\n" +
                        "            \"RoomTypeInfo.BroadNet\",\n" +
                        "            \"RoomTypeInfo.RoomBedInfos\",\n" +
                        "            \"RoomInfo.ApplicabilityInfo\",\n" +
                        "            \"RoomInfo.RoomFGToPPInfo\",\n" +
                        "            \"RoomInfo.ChannelLimit\",\n" +
                        "            \"RoomInfo.RoomTags\",\n" +
                        "            \"RoomInfo.AreaApplicabilityInfo\",\n" +
                        "            \"RoomInfo.BookingRules\",\n" +
                        "            \"RoomInfo.Smoking\",\n" +
                        "            \"RoomInfo.IsNeedCustomerTelephone\",\n" +
                        "            \"RoomTypeInfo.Pictures\",\n" +
                        "            \"RoomTypeInfo.BroadNet\",\n" +
                        "            \"RoomTypeInfo.Smoking\",\n" +
                        "            \"RoomInfo.BroadNet\",\n" +
                        "            \"RoomInfo.ExpressCheckout\",\n" +
                        "            \"RoomInfo.RoomGiftInfos\",\n" +
                        "            \"RoomInfo.RoomPromotions\",\n" +
                        "            \"RoomInfo.HotelPromotions\",\n" +
                        "            \"RoomInfo.MaskCampaignInfos\",\n" +
                        "            \"RoomInfo.RoomTags.HotelDiscount\"\n" +
                        "],\n" +
                        "            \"SearchTags\": [{\n" +
                        "            \"Code\": \"IsOutputHiddenMaskRoom\"\n" +
                        "        }, {\n" +
                        "            \"Code\": \"IsOutputLimitDestinationRoom\"\n" +
                        "        }]\n" +
                        "    },\n" +
                        "    \"PagingSettings\":{      \n" +
                        "        \"PageSize\":" + PageSize + ",\n" +
                        "       \"LastRecordID\":\"" + LastRecordID + "\"\n" +
                        "    }\n" +
                        "}";
                log.info("请求的json:{}", json);
                String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
                String result = HttpClientUtil.doPostJson(serverHost, map, json);
                String ack = ResponseToBeanUtil.getResponseStatus(result);
                if (!"Success".equals(ack)) {
                    log.warn("{}请求出现错误!错误信息{}", Thread.currentThread().getName(), result);
                    return R.fail();
                }
                //获取实体集合，添加至mongodb
                List<SubRoomDetail> subRoomStaticBean = ResponseToBeanUtil.getSubRoomStaticBean(result, HotelID);
                mongoTemplate.insert(subRoomStaticBean, "SubRoomDetail");
                LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
            } while (!"".equals(LastRecordID));
        }
        return R.ok();
    }

    @ApiOperation(value = "直连查询")
    @GetMapping("/query/rate")
    @Async
    public void queryRate() throws InterruptedException {
        int PageSize = 1;
        Map map = putParam();
        map.put("ICODE", rateDirect);
        String UUID = java.util.UUID.randomUUID().toString();
        //获得Access Token
        String accessToken = getAccessToken(UUID);
        map.put("Token", accessToken);
        String json = "";
        log.info("请求的json:{}", json);
        String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
        String result = HttpClientUtil.doPostJson(serverHost, map, json);
        System.out.println(result);
    }
}
