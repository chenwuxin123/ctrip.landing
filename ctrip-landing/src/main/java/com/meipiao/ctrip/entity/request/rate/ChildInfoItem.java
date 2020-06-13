package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:35
 */
@Data
public class ChildInfoItem implements Serializable {
    /// <summary>
    /// 儿童年龄
    /// </summary>
    private String Age ;

    /// <summary>
    /// 儿童数
    /// </summary>
    private String Count ;
}
