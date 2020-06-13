package com.meipiao.ctrip.utils;


import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数据签名，签名是为了解决请求的唯一性、时效性。
 * 签名方法：md5(timestamp + md5(data + appkey) + secretKey) 。
 * <p>
 * 注意要小写，加号指拼接字符串操作。
 *
 * @Author: Chenwx
 * @Date: 2020/5/12 17:24
 */
@Slf4j
public class MD5 {

    public static String getMD5(String plainText) {
        byte[] secretBytes = null;

        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update((plainText.getBytes()));
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error("md5加密算法失败!");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) {
        Long timestamp  = 1489555911L;
        String data  = "{\"Version\":\"1.28\",\"Local\":\"zh_CN\",\"Request{\"ArrivalDate\":\"2017-03-16\",\n" +
                "\n" +
                "\"DepartureDate\":\"2017-03-17\",\"CityId\":\"0101\",\"PageIndex\":1,\"PageSize\":10,\"ResultType\":\"1,2,4\",\"PaymentType\":\"All\"}}";
        String appkey = "97f1f3804a9388663067f0eb04c2128";
        String secretKey = "431d6af690d323f99bd816215b30b156";
        String md5 = getMD5(timestamp + getMD5(data + appkey) + secretKey);
        System.err.println("加密后的值:"+md5);
    }
}
