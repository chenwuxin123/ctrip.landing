package com.meipiao.ctrip.entity.response.rate;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 9:46
 */
@Data
@Document(collection = "PriceDetail")
public class PriceDetail implements Serializable {
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
    /// 物理房型Id
    /// </summary>
    private String RoomId;

    /// <summary>
    /// 售卖房型Id(子房型)
    /// </summary>
    private String RoomCode;

    /// <summary>
    /// 政策Id（md5-Key）RoomId+RoomCode+Breakfast
    /// </summary>
    private String RatePlanId;

    /// <summary>
    /// 日期
    /// </summary>
    private String UseDay;

    /// <summary>
    /// 可定房量，10间以内显示真实房量，大于10间显示 ”10+”
    /// </summary>
    private Integer RemainingRooms;

    /// <summary>
    /// 促销优惠金额
    /// </summary>
    private BigDecimal PromoTionAmout;

    /// <summary>
    /// 促销优惠百分比
    /// </summary>
    private BigDecimal PromoTionPercent;

    /// <summary>
    /// 卖价
    /// </summary>
    private String DisplayCurrency;

    /// <summary>
    /// 结算价
    /// </summary>
    private String DisplayCostCurrency;

    /// <summary>
    ///
    /// </summary>
    private String OriginalCurrency;

    /// <summary>
    ///
    /// </summary>
    private String OriginalCostCurrency;

    /// <summary>
    /// 原价
    /// </summary>
    private String RawPrice;

    /// <summary>
    /// 币种
    /// </summary>
    private String Currency;
}
