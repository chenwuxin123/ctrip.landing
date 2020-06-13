package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:34
 */
@Data
public class DateRange implements Serializable {
    /// <summary>
    /// 价格开始日期
    /// </summary>
    private String Start ;

    /// <summary>
    /// 价格结束日期
    /// </summary>
    private String End ;
}
