package com.meipiao.ctrip.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.constant.TokenConstant;
import com.meipiao.ctrip.controller.auth.AuthorityController;
import com.meipiao.ctrip.entity.response.city.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.rate.CancelDetail;
import com.meipiao.ctrip.entity.response.rate.PolicyDetail;
import com.meipiao.ctrip.entity.response.rate.PriceDetail;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
import com.meipiao.ctrip.service.MongodbService;
import com.meipiao.ctrip.utils.*;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Chenwx
 * @Date: 2020/6/9 10:16
 */
@Slf4j
@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/api")
@Api(value = "Ctrip Data Landing API", tags = {"携程数据落地API"})
public class StaticDataController {

    @Resource
    AuthorityController authorityController;

    @Resource
    MongoTemplate mongoTemplate;

    @Resource
    RedisUtil redisUtil;

    @Resource
    MongoAggregationUtil mongoAggregationUtil;

    @Resource
    MongodbService mongodbService;

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

    @Value("${MONITOR.ROOM.INCREMENT}")
    private String roomIncrementICODE;   //监测房价、房量、房态增量变化ICODE

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

    @GetMapping("/city")
    @ApiOperation(value = "获取全量城市信息")
    /*
          SearchByType:  搜索范围描述 1.中国(包含港澳台地区) 2.海外 3.全部
          IsHaveHotel: 是否输出有酒店的城市
          PageSize: 每页记录数，最大限制5000
          LastRecordID: 首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID
     */
    public void City() throws InterruptedException {
        //获取sid aid uuid 请求的ICODE lock的UUID
        int PageSize = 5000;
        Map map = putParam();
        map.put("ICODE", cityICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        String LastRecordID = "";
        int updateCount = 0;
        do {
            //获得Access Token
            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);
            String json = RequestBeanToJson.getCityEntityReq(PageSize, LastRecordID);
            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
                break;
            }
            //获取实体集合，添加至mongodb
            ArrayList<Destination> destinationEntity = ResponseToBeanUtil.getDestinationBean(result);
            updateCount += mongodbService.updateCity(destinationEntity);
            LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
        } while (!"".equals(LastRecordID));
        log.info("此次请求全量城市信息共添加|更新了{}条数据", updateCount);
    }

    @GetMapping("/hotel/id")
    @ApiOperation(value = "获取酒店清单")
    /*
         CityID: 城市ID
         PageSize: 每页记录数，最大限制5000
         LastRecordID: 首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID
     */
    public void getHotelID() throws InterruptedException {
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
            int updateCount = 0;
            do {
                //获取Access Token
                String accessToken = getAccessToken(UUID);
                map.put("Token", accessToken);
                String json = RequestBeanToJson.getCityHotelIdReq(cityID, PageSize, LastRecordID);
                log.info("请求的json:{}", json);
                String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
                String result = HttpClientUtil.doPostJson(serverHost, map, json);
                String ack = ResponseToBeanUtil.getResponseStatus(result);
                if (!"Success".equals(ack)) {
                    log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
                    break;
                }
                //获取实体集合，添加至mongodb
                ArrayList<HotelIdDetail> hotelIdDetail = ResponseToBeanUtil.getHotelIdDetailBean(result, cityObj);
                updateCount += mongodbService.updateHotelId(hotelIdDetail);
                LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
            } while (!"".equals(LastRecordID));
            log.info("此次请求全量城市信息共添加|更新了{}条数据", updateCount);
        }
    }

    /*
        @GetMapping("/hotel/static")
        @ApiOperation(value = "获取酒店静态信息")

     */
    @Async
    public void getHotelStatic(List<String> hotelIds) throws InterruptedException {
        //获取sid aid uuid 请求的ICODE lock的UUID
        Map map = putParam();
        map.put("ICODE", hotelInfoICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        for (String HotelID : hotelIds) {
            //获得Access Token
            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);
            String json = RequestBeanToJson.getHotelStaticReq(HotelID);
            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
                break;
            }
            //获取实体集合，添加至mongodb
            HotelDetail hotelIdDetailBean = ResponseToBeanUtil.getHotelIdDetailBean(result);
            mongodbService.updateHotelStatic(hotelIdDetailBean);
        }
    }

    /*
        @GetMapping("/room/static")
        @ApiOperation(value = "获取房型静态信息")

     */
    @Async
    public void getRoomStatic(String hotelId) throws InterruptedException {

        int PageSize = 1000; //分页每次请求售卖房型数量，最大限制1000
        //获取sid aid uuid 请求的ICODE lock的UUID
        Map map = putParam();
        map.put("ICODE", roomInfoICODE);
        String UUID = java.util.UUID.randomUUID().toString();
        String LastRecordID = "";
        //获得Access Token
        String accessToken = getAccessToken(UUID);
        map.put("Token", accessToken);
        String json = RequestBeanToJson.getRoomStaticReq(hotelId, PageSize, LastRecordID);
        log.info("请求的json:{}", json);
        String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
        String result = HttpClientUtil.doPostJson(serverHost, map, json);
        String ack = ResponseToBeanUtil.getResponseStatus(result);
        if (!"Success".equals(ack)) {
            log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
            return;
        }
        //获取实体集合，添加至mongodb
        List<RoomDetail> roomStaticBean = ResponseToBeanUtil.getRoomStaticBean(result, hotelId);
        mongodbService.updateRoomStatic(roomStaticBean);
        List<SubRoomDetail> subRoomStaticBean = ResponseToBeanUtil.getSubRoomStaticBean(result, hotelId);
        mongodbService.updateSubRoomStatic(subRoomStaticBean);
    }


    /*
        @GetMapping("/query/rate")
        @ApiOperation(value = "直连查询")

     */
    @Async
    public void queryRate(String hotelId, String start, String end) throws InterruptedException {
        int PageSize = 200;//	分页每次请求售卖房型数量，结算价分销商请求该接口时若接口返回房型数量超过200时，接口默认返回200个房型
        Map map = putParam();
        map.put("ICODE", rateDirect);
        String UUID = java.util.UUID.randomUUID().toString();

        String LastRecordID = "";
        do {
            //获得Access Token
            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);
            //请求json
            /**
             * start: 定义如何输入
             * end:   定义如何输入
             */
            String json = RequestBeanToJson.getRateEntityReq(hotelId, LastRecordID, PageSize, start, end);
            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
                break;
            }
            //添加至mongodb
            List<PriceDetail> priceDetailBean = ResponseToBeanUtil.getPriceDetailBean(result, hotelId);
            List<PolicyDetail> policyDetailBean = ResponseToBeanUtil.getPolicyDetailBean(result, hotelId);
            List<CancelDetail> cancelDetailBean = ResponseToBeanUtil.getCancelDetailBean(result, hotelId);
            mongodbService.updatePriceDetail(priceDetailBean);
            mongodbService.updatePolicyDetail(policyDetailBean);
            mongodbService.updateCancelDetail(cancelDetailBean);
            LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
        } while (!"".equals(LastRecordID));
    }

    @GetMapping("/change/price")
    @ApiOperation(value = "监测房价、房量、房态增量变化接口")
    public void changePrice() throws InterruptedException {
        int PageSize = 1000;//每页最多返回几条记录
        Map map = putParam();
        map.put("ICODE", roomIncrementICODE);
        String UUID = java.util.UUID.randomUUID().toString();

        String LastRecordID = "";
        do {
            //获得Access Token
            String accessToken = getAccessToken(UUID);
            map.put("Token", accessToken);
            //请求json
//            String json = RequestBeanToJson.getIncrPriceEntityReq(LastRecordID, PageSize, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            String json = RequestBeanToJson.getIncrPriceEntityReq(LastRecordID, PageSize,"20120-06-20T17:23:09.777+08:00" );
            log.info("请求的json:{}", json);
            String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
            String result = HttpClientUtil.doPostJson(serverHost, map, json);
            String ack = ResponseToBeanUtil.getResponseStatus(result);
            if (!"Success".equals(ack)) {
                log.warn("{}请求出现错误!错误信息{}  --请检查输入参数是否正确", Thread.currentThread().getName(), result);
                break;
            }
            //如何处理
            System.out.println(result);
            LastRecordID = ResponseToBeanUtil.getLastRecordID(result);
        } while (!"".equals(LastRecordID));
    }

}
