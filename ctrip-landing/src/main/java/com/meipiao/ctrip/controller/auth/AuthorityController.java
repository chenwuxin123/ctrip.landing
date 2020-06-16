package com.meipiao.ctrip.controller.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.constant.TokenConstant;
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
@RequestMapping("/auth")
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

    private final String tokenKey = TokenConstant.TOKENKEY; //过期key

    private final String persistenceTokenKey = TokenConstant.PERSISTENCETOKENKEY; //持久化key

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    //初始化Access Token（GET方式）
//    @GetMapping("/access/token")
    @ApiOperation(value = "初始化Access Token（GET方式）")
    public String accessToken() {
        map.put("AID", aid);
        map.put("SID", sid);
        map.put("KEY", key);
        String serverHost = httpAddress + "/openserviceauth/authorize.ashx";
        String response = HttpClientUtil.doGet(serverHost, map);
        JSONObject result = JSONObject.parseObject(response);
        //将返回的token信息存储到redis中
        String access_token = saveTokenToRedis(result);
        return access_token;
    }

    //更新ACCESS TOKEN（GET方式）
//    @GetMapping("/refresh/token")
    @ApiOperation(value = "更新ACCESS TOKEN（GET方式））")
    public String refreshToken() {
        String access_token = null;
        // 根据持久化的key取得对应的值
        redisUtil.del(tokenKey);
        Map<Object, Object> getMap = redisUtil.hmget(persistenceTokenKey);
        if (getMap.size() > 0) {
            for (Map.Entry<Object, Object> entry : getMap.entrySet()) {
                String accessToken = (String) entry.getKey();
                String refreshToken = (String) entry.getValue();
                if (accessToken != null && refreshToken != null) {
                    map.put("AID", aid);
                    map.put("SID", sid);
                    map.put("Refresh_Token", refreshToken);
                    String serverHost = httpAddress + "/openserviceauth/refresh.ashx";
                    String response = HttpClientUtil.doGet(serverHost, map);
                    JSONObject result = JSONObject.parseObject(response);
                    Integer errCode = result.getInteger("ErrCode");
                    //如果没有错误代码
                    if (errCode == null) {
                        //将返回的token信息存储到redis中
                        access_token = saveTokenToRedis(result);
                        return access_token;
                    } else {
                        String errMsg = result.getString("ErrMsg");
                        switch (errCode) {
                            case 232:
                                String dir232 = "INVALID_TOKEN".equals(errCode) ? "验证token失败了" : "请优先去获取AccessToken";
                                log.error("错误码：{},错误类型: 分销商参数验证无效,错误信息: {},错误说明: {}", errCode, errMsg, dir232);
                                access_token = accessToken();
                                return access_token;
                            case 233:
                                log.error("错误码：{},错误类型: 接口频次超频,错误信息: FREQUENCY_LIMIT_REACHED,错误说明: 接口频次超频", errCode);
                                break;
                            case 234:
                                String dir234 = "INTERNAL_INTERFACE_ERROR".equals(errCode) ? "内部转发服务出错" : "转发服务不存在，请检查Icode是否正确";
                                log.error("错误码：{},错误类型: 背后服务转发失败,错误信息: {},错误说明: {}", errCode, errMsg, dir234);
                                break;
                            case 235:
                                String dir235 = "ACCESS_DENIED".equals(errCode) ? "错误过多熔断" : "超时错误过多造成熔断";
                                log.error("错误码：{},错误类型: 错误比例过高造成熔断,错误信息: {},错误说明: {}", errCode, errMsg, dir235);
                                break;
                            case 1001:
                                log.error("错误码：{},错误类型: 缺失Auth请求参数,错误信息: INVALID_PARAMETERS,错误说明: 请检查aid,sid,userkey是否正确", errCode);
                                break;
                            case 1005:
                                log.error("错误码：{},错误类型: 重置Token密钥失效,错误信息: INVALID_REFRESHTOKEN,错误说明: 请检查重置Refreshtoken是否有误", errCode);
                                access_token = accessToken();
                                return access_token;
                            default:
                                log.error("未知请求错误");
                        }
                    }
                }
            }
        } else {
            log.warn("未初始化token！正在初始化...");
            access_token = accessToken();
        }
        return access_token;
    }

    //将返回的token信息存储到redis中(临时+过期)
    private String saveTokenToRedis(JSONObject result) {
        //将返回的token信息存储到redis中
        String access_token = result.getString("Access_Token");
        Integer expires_in = result.getInteger("Expires_In") - 5;  //过期时间 -5s
        String refresh_token = result.getString("Refresh_Token");
        //将持久化中上一个key删除
        redisUtil.del(persistenceTokenKey);
        boolean saveResultExpires = redisUtil.hset(tokenKey, access_token, refresh_token, expires_in);
        boolean saveResult = redisUtil.hset(persistenceTokenKey, access_token, refresh_token);
        if (saveResultExpires && saveResult) {
            log.info("{}:token{}--缓存成功!", LocalDateTime.now(), access_token);
            return access_token;
        }
        log.error("{}:token--{}缓存失败!", LocalDateTime.now(), access_token);
        return "fail";
    }

}
