package com.meipiao.ctrip.entity.response.room;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 13:59
 */
@Data
@Document(collection = "SubRoomDetail")
public class SubRoomDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String Id;
    private long UpdateTimeStamp;
    private String MasterHotelNum;

    /// <summary>
    /// 房型Id
    /// </summary>
    private long SubRoomId;

    /// <summary>
    /// true:有效 false:无效
    /// </summary>
    private Integer DataFlag;

    /// <summary>
    /// 无效原因
    /// </summary>
    private String DataFlagRemark;

    /// <summary>
    /// 子房型Id
    /// </summary>
    private String RoomCode;

    /// <summary>
    /// 房型Id
    /// </summary>
    private String RoomId;

    /// <summary>
    /// 行政商务房(EDM 特惠)
    /// </summary>
    private String RoomName;

    /// <summary>
    /// 支付类型
    /// <para>FG现付</para>
    /// <para>PP预付</para>
    /// </summary>
    private String PayType;

    /// <summary>
    /// 房间数量
    /// </summary>
    private Integer RoomQuantity;

    /// <summary>
    /// 最大人数
    /// </summary>
    private Integer MaxOccupancy;

    /// <summary>
    /// 面积
    /// </summary>
    private String AreaRange;

    /// <summary>
    /// 楼层
    /// </summary>
    private String FloorRange;

    /// <summary>
    /// 窗户类型
    /// </summary>
    private Integer HasWindow;

    /// <summary>
    /// 加床费
    /// <para>0免费加床</para>
    /// <para>大于0表示加床费用</para>
    /// <para>unknow未知</para>
    /// </summary>
    private String ExtraBedFee;

    /// <summary>
    /// 是否钟点房
    /// </summary>
    private String IsHourlyRoom;

    /// <summary>
    /// 房型是否直连
    /// </summary>
    private String IsFromAPI;

    /// <summary>
    /// 是否展示代理标签
    /// </summary>
    private String IsShowAgencyTag;

    /// <summary>
    /// 开票方式
    /// <para>1携程开票</para>
    /// <para>2酒店开票</para>
    /// <para>3供应商开票</para>
    /// </summary>
    private Integer InvoiceType;

    /// <summary>
    /// 发票模式
    /// <para>0普通开票</para>
    /// <para>1预约开票</para>
    /// </summary>
    private String InvoiceMode;

    /// <summary>
    /// 是否提供专票
    /// </summary>
    private String IsSupportSpecialInvoice;

    /// <summary>
    /// 是否接受客人在订单填写自定义备注
    /// </summary>
    private String ReceiveTextRemark;

    /// <summary>
    /// 酒店是否需要客人联系电话
    /// </summary>
    private String IsNeedCustomerTelephone;

    /// <summary>
    /// 是否不可订房型
    /// </summary>
    private String IsClosed;

    /// <summary>
    /// 是否可定价
    /// </summary>
    private String IsAllowRepricing;

    /// <summary>
    /// 唯一有效，是否可吸烟
    /// <para>0未知</para>
    /// <para>1禁烟</para>
    /// <para>2吸烟</para>
    /// </summary>
    private String IsAllowSmoking;

    /// <summary>
    /// 日期限制
    /// </summary>
    private List<TimeLimitInfo> TimeLimitInfos;
}
