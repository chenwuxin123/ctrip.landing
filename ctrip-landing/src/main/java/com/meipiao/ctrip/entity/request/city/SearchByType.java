package com.meipiao.ctrip.entity.request.city;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 16:32
 */
@Data
public class SearchByType implements Serializable {
    /// <summary>
    /// 搜索范围描述
    /// <para>1.中国(包含港澳台地区)</para>
    /// <para>2.海外</para>
    /// <para>3.全部</para>
    /// </summary>
    private String SearchType ;

    /// <summary>
    /// 是否输出有酒店的城市
    /// </summary>
    private String IsHaveHotel ;
}
