package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * 政策请求体
 * @Author: Chenwx
 * @Date: 2020/6/12 10:32
 */
@Data
public class GetRateEntityReq implements Serializable {
    /// <summary>
    /// 
    /// </summary>
    private RateSearchCandidate SearchCandidate ;

    /// <summary>
    /// 基础配置
    /// </summary>
    private RateSettings Settings ;

    /// <summary>
    /// 
    /// </summary>
    private com.meipiao.ctrip.entity.request.page.PagingSettings PagingSettings ;
    
}
