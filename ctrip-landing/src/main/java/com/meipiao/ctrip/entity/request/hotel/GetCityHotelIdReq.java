package com.meipiao.ctrip.entity.request.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取城市酒店Id
 * @Author: Chenwx
 * @Date: 2020/6/12 16:47
 */
@Data
public class GetCityHotelIdReq implements Serializable {
    /// <summary>
    /// 
    /// </summary>
    private HSearchCandidate SearchCandidate ;
    /// <summary>
    /// 
    /// </summary>
    private com.meipiao.ctrip.entity.request.page.PagingSettings PagingSettings ;
}
