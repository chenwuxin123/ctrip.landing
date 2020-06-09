package com.meipiao.ctrip.common;

/**
 * @Author: Chenwx
 * @Date: 2020/4/23 17:26
 */
public interface ResultCode {
    Integer SUCCESS = 200000;//成功

    Integer ERROR = 200001;//错误

    Integer FAIL = 200006;//操作失效

    Integer HALF_SUCCESS = 200008;//成功请求但是没有返回数据
}
