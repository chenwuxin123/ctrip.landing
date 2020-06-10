package com.meipiao.ctrip.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.common.R;
import com.meipiao.ctrip.constant.TokenConstant;
import com.meipiao.ctrip.controller.auth.AuthorityController;
import com.meipiao.ctrip.entity.response.Destination;
import com.meipiao.ctrip.utils.HttpClientUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import com.meipiao.ctrip.utils.ResponseToBeanUtil;
import com.mongodb.BasicDBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Chenwx
 * @Date: 2020/6/9 10:16
 */
@RestController
@Slf4j
@RequestMapping("/api")
@Api(value = "Ctrip Data Landing API", tags = {"携程数据落地API"})
public class StaticDataController {

    @Autowired
    AuthorityController authorityController;

    @Autowired
    MongoTemplate mongoTemplate;

    @Resource
    RedisUtil redisUtil;

    @Value("${CITY.ICODE}")
    private String cityICODE;   //获取全量城市信息ICODE

    @Value("${HOTELID.ICODE}")
    private String hotelidICODE;   //获取酒店清单ICODE

    @Value("${HOTEL.INFO.ICODE}")
    private String hotelInfoICODE;   //获取酒店静态信息ICODE

    @Value("${ROOM.INFO.ICODE}")
    private String roomInfoICODE;   //获取房型静态信息ICODE

    @Value("${HOTEL.REVIEWS.ICODE}")
    private String hotelReviewsICODE;   //查询酒店点评信息ICODE

    @Value("${HOTEL.COMMENT.ICODE}")
    private String hotelCommentICODE;   //查询酒店维度总点评数据ICODE

    @Value("${HOTEL.DELETE.ICODE}")
    private String hotelDeleteICODE;   //查询删除的酒店点评ICODE


    @Value("${ctrip.http.address}")
    private String httpAddress;     //携程请求地址

    @Value("${ctrip.aid}")
    private String aid;             //aid

    @Value("${ctrip.sid}")
    private String sid;             //sid

    @Value("${UniqueID}")          //UniqueID
    private String UniqueID;

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private final String tokenKey = TokenConstant.TOKENKEY; //有效token对应的key


    private Map putParam() {
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SearchType", value = "搜索范围描述 1.中国(包含港澳台地区) 2.海外 3.全部", dataType = "String", example = "1", required = true),
            @ApiImplicitParam(name = "IsHaveHotel", value = "是否输出有酒店的城市", dataType = "String", example = "F", required = true),
            @ApiImplicitParam(name = "PageSize", value = "每页记录数，最大限制5000", example = "2", required = true)
//            @ApiImplicitParam(name = "LastRecordID", value = "首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID", dataType = "String")
    })
    public R City(String SearchType, String IsHaveHotel, Integer PageSize) throws InterruptedException {
        String LastRecordID = "";
        String UUID = java.util.UUID.randomUUID().toString();
        Map storeMap = putParam();
        //请求的ICODE
        storeMap.put("ICODE", cityICODE);
        //获得Access Token
        String accessToken = getAccessToken(UUID);
        this.map.put("Token", accessToken);
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
        String result = HttpClientUtil.doPostJson(serverHost, this.map, json);
        String ack = ResponseToBeanUtil.getResponseStatus(result);
        if (!"Success".equals(ack)) {
            return R.error().data(result);
        }
        String lastRecordID = ResponseToBeanUtil.getLastRecordID(result);
        //获取实体集合，添加至mongodb
        ArrayList<Destination> destinationEntity = ResponseToBeanUtil.getDestinationEntity(result);
        mongoTemplate.insert(destinationEntity, "Destination");
        return R.ok();
    }

    @ApiOperation(value = "获取酒店清单")
    @GetMapping("/hotel/list")
    @Async
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "CityID", value = "城市ID", dataType = "String", example = "1", required = true),
            @ApiImplicitParam(name = "PageSize", value = "每页记录数，最大限制5000", example = "2", required = true)
//            @ApiImplicitParam(name = "LastRecordID", value = "首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID", dataType = "String")
    })
    public R getHotelIDList(Integer PageSize) throws InterruptedException {
        /*
            获取mongodb中所有CityID 进行遍历查询HotelId
        */
        //查找城市id
        Aggregation aggregation =
                Aggregation.newAggregation(
                        Aggregation.project("CityID", "CityName", "ProvinceID", "ProvinceName")
                );
        AggregationResults<BasicDBObject> cityInfo = mongoTemplate.aggregate(aggregation, "Destination", BasicDBObject.class);
        for (BasicDBObject basicDBObject : cityInfo) {
            String cityJson = basicDBObject.toJson();
            //cityObj是城市信息
            JSONObject cityObj = JSONObject.parseObject(cityJson);
            String cityID = cityObj.getString("CityID");
            //执行业务
            String LastRecordID = "";
            String UUID = java.util.UUID.randomUUID().toString();
            Map storeMap = putParam();
            //请求的ICODE
            storeMap.put("ICODE", hotelidICODE);
            //获取Access Token
            String accessToken = getAccessToken(UUID);
            this.map.put("Token", accessToken);
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
                return R.error().data(result);
            }
            String lastRecordID = ResponseToBeanUtil.getLastRecordID(result);
            //获取实体集合，添加至mongodb
            ResponseToBeanUtil.getHotelIdDetail(result,cityObj);
        }
        return R.ok();
    }
}
