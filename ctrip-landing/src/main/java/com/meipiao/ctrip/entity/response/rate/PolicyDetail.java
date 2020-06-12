package com.meipiao.ctrip.entity.response.rate;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 9:46
 */
@Data
@Document(collection = "PolicyDetail")
public class PolicyDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String Id;

    /// <summary>
    /// 时间戳
    /// </summary>
    private long UpdateTimeStamp;

    /// <summary>
    /// 酒店id
    /// </summary>
    private String MasterHotelNum;

    /// <summary>
    /// 房型Id
    /// </summary>
    private String RoomId;

    /// <summary>
    /// 售卖房型Id
    /// </summary>
    private String RoomCode ;

    /// <summary>
    /// 政策Id（md5-Key）RoomId+RoomCode+Breakfast
    /// </summary>
    private String RatePlanId ;

    /// <summary>
    /// 早餐
    /// </summary>
    private Integer Breakfast ;

    /// <summary>
    /// 午餐
    /// </summary>
    private Integer Lunch ;

    /// <summary>
    /// 晚餐
    /// </summary>
    private Integer Dinner ;

    // /// <summary>
    // /// 床型
    // /// </summary>
    // private Integer BedType ;

    ///// <summary>
    ///// 床名称
    ///// </summary>
    // private String BedName ;

    // /// <summary>
    // /// 最大人数
    // /// </summary>
    // private Integer MaxOccupancy ;

    ///// <summary>
    ///// 售卖房型是否可立即确认(仅代表订单确认速度，分销商仍需通过相关接口同步携程订单状态)
    ///// <para></para>True-该房型为立即确认房型
    ///// <para></para>false-该房型非立即确认房型
    ///// </summary>
    //private String IsInstantConfirm ;

    ///// <summary>
    ///// 房型是否直连
    ///// </summary>
    //private bool IsFromAPI ;
}
