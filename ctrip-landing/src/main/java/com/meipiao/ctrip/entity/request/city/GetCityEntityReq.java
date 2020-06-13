package com.meipiao.ctrip.entity.request.city;

import com.meipiao.ctrip.entity.request.page.PagingSettings;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取全量城市信息实体
 * @Author: Chenwx
 * @Date: 2020/6/12 16:30
 */
@Data
public class GetCityEntityReq implements Serializable {
    /// <summary>
    /// 
    /// </summary>
    private com.meipiao.ctrip.entity.request.city.SearchCandidate SearchCandidate ;
    /// <summary>
    /// 
    /// </summary>
    private com.meipiao.ctrip.entity.request.page.PagingSettings PagingSettings ;
}
