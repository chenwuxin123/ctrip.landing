package com.meipiao.ctrip.entity.response.room;

import com.meipiao.ctrip.entity.response.rate.RoomPriceRes;
import lombok.Data;
import org.springframework.data.annotation.Transient;
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

    private Long updateTimeStamp;
    private Long hotelId;

    /// <summary>
    /// 房型Id
    /// </summary>
    private Long subRoomId;

    /// <summary>
    /// 无效原因
    /// </summary>
    private String dataFlagRemark;

    /// <summary>
    /// 子房型Id
    /// </summary>
    private Long roomCode;

    /// <summary>
    /// 房型Id
    /// </summary>
    private Long roomId;

    /// <summary>
    /// 行政商务房(EDM 特惠)
    /// </summary>
    private String roomName;

    /// <summary>
    /// 支付类型
    /// <para>FG现付</para>
    /// <para>PP预付</para>
    /// </summary>
    private String payType;

    /// <summary>
    /// 房间数量
    /// </summary>
    private Integer roomQuantity;

    /// <summary>
    /// 最大人数
    /// </summary>
    private Integer maxOccupancy;

    /// <summary>
    /// 面积
    /// </summary>
    private String areaRange;

    /// <summary>
    /// 楼层
    /// </summary>
    private String floorRange;

    /// <summary>
    /// 窗户类型
    /// </summary>
    private Integer hasWindow;

    /// <summary>
    /// 加床费
    /// <para>0免费加床</para>
    /// <para>大于0表示加床费用</para>
    /// <para>unknow未知</para>
    /// </summary>
    private String extraBedFee;

    /// <summary>
    /// 是否钟点房
    /// </summary>
    private String isHourlyRoom;

    /// <summary>
    /// 房型是否直连
    /// </summary>
    private String isFromAPI;

    /// <summary>
    /// 是否展示代理标签
    /// </summary>
    private String isShowAgencyTag;

    /// <summary>
    /// 开票方式
    /// <para>1携程开票</para>
    /// <para>2酒店开票</para>
    /// <para>3供应商开票</para>
    /// </summary>
    private Integer invoiceType;

    /// <summary>
    /// 发票模式
    /// <para>0普通开票</para>
    /// <para>1预约开票</para>
    /// </summary>
    private String invoiceMode;

    /// <summary>
    /// 是否提供专票
    /// </summary>
    private String isSupportSpecialInvoice;

    /// <summary>
    /// 是否接受客人在订单填写自定义备注
    /// </summary>
    private String receiveTextRemark;

    /// <summary>
    /// 酒店是否需要客人联系电话
    /// </summary>
    private String isNeedCustomerTelephone;

    /// <summary>
    /// 是否不可订房型
    /// </summary>
    private String isClosed;

    /// <summary>
    /// 是否可定价
    /// </summary>
    private String isAllowRepricing;

    /// <summary>
    /// 唯一有效，是否可吸烟
    /// <para>0未知</para>
    /// <para>1禁烟</para>
    /// <para>2吸烟</para>
    /// </summary>
    private String isAllowSmoking;

    /// <summary>
    /// 日期限制
    /// </summary>
    private List<TimeLimitInfo> timeLimitInfos;

    /// <summary>
    /// 预定价格信息
    /// </summary>
    @Transient
    private RoomPriceRes roomPriceRes;
}
