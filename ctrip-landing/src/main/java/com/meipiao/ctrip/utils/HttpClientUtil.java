package com.meipiao.ctrip.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 发送http请求工具包
 *
 * @Author: Chenwx
 * @Date: 2020/6/8 8:10
 */
@Slf4j

public class HttpClientUtil {

    public static final String DEF_CHATSET = "UTF-8";

    //HttpClient发送GET有参
    public static String doGet(String url, Map<String, String> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建url带参数
            if (params != null) {
                url = url + "?" + urlencode(params);
            }
            log.info("请求的地址:{}", url);

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(url);
            long start = System.currentTimeMillis();
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            long end = System.currentTimeMillis();
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), DEF_CHATSET);
                log.info("执行时间{}ms", end - start);
            }
        } catch (Exception e) {
            log.error("{}方法中http请求发生异常:{}", HttpClientUtil.class.getName(), e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("{}中响应异常:{}", HttpClientUtil.class.getName(), e.getMessage());
            }
        }

        return resultString;
    }


    //post有参传递json数据
    public static String doPostJson(String url, Map<String, String> params, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建url带参数
            if (params != null) {
                url = url + "?" + urlencode(params);
            }
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpclient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), DEF_CHATSET);
            // 执行http请求
        } catch (Exception e) {
            log.error("{}方法中http请求发生异常:{}", HttpClientUtil.class.getName(), e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("{}中响应异常:{}", HttpClientUtil.class.getName(), e.getMessage());
            }
        }
        return resultString;
    }

    //将map型转为请求参数型
    private static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
