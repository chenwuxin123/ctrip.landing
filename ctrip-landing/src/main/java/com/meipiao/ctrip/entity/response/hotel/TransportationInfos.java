package com.meipiao.ctrip.entity.response.hotel;

import lombok.Data;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/10 13:18
 */
@Data
public class TransportationInfos {
    /** 目的地名称 **/
    private String name;
    /** 距离 **/
    private Double distance;
    /** 描述 **/
    private String directions;
    /** 预估时间 **/
    private Double timeTaken;

}
