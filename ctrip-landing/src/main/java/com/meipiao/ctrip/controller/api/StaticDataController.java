package com.meipiao.ctrip.controller.api;

import com.meipiao.ctrip.common.R;
import com.meipiao.ctrip.constant.TokenConstant;
import com.meipiao.ctrip.controller.auth.AuthorityController;
import com.meipiao.ctrip.utils.HttpClientUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

     /*
                if ( redis.token ){
                    执行业务
                } else {
                    synchronized(this){
                    refreshToken();
                    }
                    自旋
                }
         */

    private Map putParam() {
        map.put("AID", aid);
        map.put("SID", sid);
        map.put("UUID", UniqueID);
        map.put("mode", "1");
        map.put("format", "json");
        return map;
    }

    @ApiOperation(value = "获取全量城市信息")
    @GetMapping("/city")
    @Async
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SearchType", value = "搜索范围描述 1.中国(包含港澳台地区) 2.海外 3.全部", dataType = "String", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "IsHaveHotel", value = "是否输出有酒店的城市", dataType = "String", defaultValue = "F", required = true),
            @ApiImplicitParam(name = "PageSize", value = "每页记录数，最大限制5000", dataType = "Integer", defaultValue = "2", required = true),
            @ApiImplicitParam(name = "LastRecordID", value = "首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID", dataType = "String"),
    })
    public R City(String SearchType, String IsHaveHotel, Integer PageSize, String LastRecordID) {
        Map storeMap = putParam();
        //请求的ICODE
        storeMap.put("ICODE", cityICODE);
        //获取Access Token
        Map<Object, Object> tokenMap = redisUtil.hmget(tokenKey);
        if (tokenMap.size() == 0) {
            //刷新token 后续使用redis锁，没有拿到锁的线程进行自旋
            synchronized (this) {
                String access_token = authorityController.refreshToken();
                this.map.put("Token", access_token);
            }
        } else {
            for (Object token : tokenMap.keySet()) {
                this.map.put("Token", token.toString());
            }
        }
        String json = "{\n" +
                "    \"SearchCandidate\":{\n" +
                "        \"SearchByType\":{\n" +
                "            \"SearchType\":" + SearchType + "\",\n" +
                "            \"IsHaveHotel\"::" + IsHaveHotel + "\n" +
                "        }\n" +
                "    },\n" +
                "    \"PagingSettings\":{\n" +
                "        \"PageSize\":" + PageSize + "\",\n" +
                "        \"LastRecordID:\"" + LastRecordID + "\"\n" +
                "    }\n" +
                "}";
        log.info("请求的json:{}", json);
        String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
        String result = HttpClientUtil.doPostJson(serverHost, this.map, json);
        System.err.println(result);
        return R.ok();
    }

    @ApiOperation(value = "获取酒店清单")
    @GetMapping("/hotel/list")
    @Async
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CityID", value = "城市ID", dataType = "String", defaultValue = "1", required = true),
            @ApiImplicitParam(name = "PageSize", value = "每页记录数，最大限制5000", dataType = "Integer", defaultValue = "10", required = true),
            @ApiImplicitParam(name = "LastRecordID", value = "首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID", dataType = "String"),
    })
    public R getHotelIDList(String CityID, Integer PageSize, String LastRecordID) {
        Map storeMap = putParam();
        //请求的ICODE
        storeMap.put("ICODE", cityICODE);
        //获取Access Token
        Map<Object, Object> tokenMap = redisUtil.hmget(tokenKey);
        if (tokenMap.size() == 0) {
            //刷新token 后续使用redis锁，没有拿到锁的线程进行自旋
            synchronized (this) {
                String access_token = authorityController.refreshToken();
                map.put("Token", access_token);
            }
        } else {
            for (Object token : tokenMap.keySet()) {
                map.put("Token", token.toString());
            }
        }
        String json = "{\n" +
                "    \"SearchCandidate\": {\n" +
                "        \"SearchByCityID\": {\n" +
                "            \"CityID\": \"" + CityID + "\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"PagingSettings\": {\n" +
                "        \"PageSize\":" + PageSize + "\",\n" +
                "        \"LastRecordID:\"" + LastRecordID + "\"\n" +
                "    }\n" +
                "}";
        log.info("请求的json:{}", json);
        String serverHost = httpAddress + "/openservice/serviceproxy.ashx";
        String result = HttpClientUtil.doPostJson(serverHost, map, json);
        System.err.println(result);
        return R.ok();
    }
}
