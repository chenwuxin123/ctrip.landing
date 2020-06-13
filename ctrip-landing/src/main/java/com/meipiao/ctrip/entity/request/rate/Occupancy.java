package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:36
 */
@Data
public class Occupancy implements Serializable {
    /// <summary>
    /// 成人数
    /// </summary>
    private int Adult ;

    /// <summary>
    /// 儿童数
    /// </summary>
    private String Child ;

    /// <summary>
    /// 儿童信息
    /// </summary>
    private List<ChildInfoItem> ChildInfo ;
}
