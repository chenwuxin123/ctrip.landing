package com.meipiao.ctrip.entity.response.rate;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 16:49
 */
@Data
public class RoomPriceRes {

    /// <summary>
    /// 酒店id
    /// </summary>
    private Long hotelId;

    /// <summary>
    /// 房型Id
    /// </summary>
    private Long roomId;

    /// <summary>
    /// 售卖房型Id
    /// </summary>
    private Long roomCode;

    /// <summary>
    /// 房间名称
    /// </summary>
    private String roomName;

    /// <summary>
    /// 取消政策的生效时间
    /// </summary>
    private String start;

    /// <summary>
    /// 取消政策的失效时间
    /// </summary>
    private String end;

    /// <summary>
    /// 最晚预订时间
    /// </summary>
    private String latestReserveTime;

    /// <summary>
    /// 总价
    /// </summary>
    private Double totalPrice;

    /// <summary>
    /// 均价
    /// </summary>
    @Transient
    private Double averagePrice;

    /// <summary>
    /// 支付类型，PP-预付，FG-到付
    /// </summary>
    private String payType;

    /// <summary>
    /// 售卖房型是否可预订，true-可预订，false-不可预订
    /// </summary>
    private Boolean canReserve;

    /// <summary>
    /// 售卖房型是否需担保，true-需担保，false-不需担保
    /// </summary>
    private Boolean guarantee;

    /// <summary>
    /// 可定房量，10间以内显示真实房量，大于10间显示 ”10+”
    /// </summary>
    private String remainingRooms;

    /// <summary>
    /// 是否已包含促销
    /// </summary>
    private Boolean promotion;

    /// <summary>
    /// 每日价格详情(包含餐信息)
    /// </summary>
    private List<DailyPrices> dailyPrices;

}
