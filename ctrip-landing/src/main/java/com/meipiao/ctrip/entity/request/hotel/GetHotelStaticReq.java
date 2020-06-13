package com.meipiao.ctrip.entity.request.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 8:24
 */
@Data
public class GetHotelStaticReq implements Serializable {
    private CtripHotelId SearchCandidate ;
    /// <summary>
    /// 
    /// </summary>
    private Settings Settings ;
}
