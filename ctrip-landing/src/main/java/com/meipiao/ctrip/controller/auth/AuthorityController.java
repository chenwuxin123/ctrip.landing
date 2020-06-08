package com.meipiao.ctrip.controller.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.utils.HttpClientUtil;
import com.meipiao.ctrip.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 权限认证接口
 *
 * @Author: Chenwx
 * @Date: 2020/6/8 9:04
 */
@Api(value = "Authority Authentication API", tags = {"权限认证接口"})
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthorityController {

    @Value("${ctrip.http.address}")
    private String httpAddress;     //携程请求地址

    @Value("${ctrip.aid}")
    private String aid;             //aid

    @Value("${ctrip.sid}")
    private String sid;             //sid

    @Value("${ctrip.key}")
    private String key;             //key

    @Resource
    RedisUtil redisUtil;

    private final String tokenKey = "ctrip:token"; //过期key

    private final String persistenceTokenKey = "ctrip:persistence:token"; //持久化key

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    //初始化Access Token（GET方式）
    @ApiOperation(value = "初始化Access Token（GET方式）")
    @GetMapping("/access/token")
    public void accessToken() {
        map.put("AID", aid);
        map.put("SID", sid);
        map.put("KEY", key);
        String response = HttpClientUtil.doGet(httpAddress, map);
        JSONObject result = JSONObject.parseObject(response);
        //将返回的token信息存储到redis中
        saveTokenToRedis(result);
    }

    //更新ACCESS TOKEN（GET方式）
    @ApiOperation(value = "更新ACCESS TOKEN（GET方式））")
    @GetMapping("/refresh/token")
    public void refreshToken() {
        // 根据持久化的key取得对应的值
        Map<Object, Object> getMap = redisUtil.hmget(persistenceTokenKey);
        for (Map.Entry<Object, Object> entry : getMap.entrySet()) {
            String accessToken = (String) entry.getKey();
            String refreshToken = (String) entry.getValue();
            if (accessToken != null && refreshToken != null) {
                map.put("AID", aid);
                map.put("SID", sid);
                map.put("Refresh_Token", refreshToken);
                String response = HttpClientUtil.doGet(httpAddress, map);
                JSONObject result = JSONObject.parseObject(response);
                //将返回的token信息存储到redis中
                saveTokenToRedis(result);
                //将持久化中上一个key删除
                redisUtil.hdel(persistenceTokenKey, accessToken);
            }
        }
    }

    //将返回的token信息存储到redis中(临时+过期)
    private boolean saveTokenToRedis(JSONObject result) {
        //将返回的token信息存储到redis中
        String access_token = result.getString("Access_Token");
        Integer expires_in = result.getInteger("Expires_In") - 1;  //过期时间 -1s
        String refresh_token = result.getString("Refresh_Token");
        boolean saveResultExpires = redisUtil.hset(tokenKey, access_token, refresh_token, expires_in);
        boolean saveResult = redisUtil.hset(persistenceTokenKey, access_token, refresh_token);
        if (saveResultExpires && saveResult) {
            log.info("{}:token{}缓存成功!", LocalDateTime.now(), access_token);
            return true;
        }
        log.error("{}:token{}缓存失败!", LocalDateTime.now(), access_token);
        return false;
    }

}
