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
@Document(collection = "RoomDetail")
public class RoomDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String Id;
    private long UpdateTimeStamp;
    private String MasterHotelNum;
    /// <summary>
    /// 物理房型信息
    /// </summary>
    private long MainRoomId;

    /// <summary>
    /// true:有效 false:无效
    /// </summary>
    private Integer DataFlag;


    /// <summary>
    /// 无效原因
    /// </summary>
    private String DataFlagRemark;

    /// <summary>
    /// 房型Id
    /// </summary>
    private String RoomId;

    /// <summary>
    /// 房型名称
    /// <para></para>行政商务房
    /// </summary>
    private String RoomTypeName;

    /// <summary>
    /// 房型英文名称
    /// <para></para>行政商务房
    /// </summary>
    private String RoomTypeName_En;

    /// <summary>
    /// 房型归属
    /// <para>标准间</para>
    /// </summary>
    private String StandardRoomType;

    /// <summary>
    /// 房间数
    /// </summary>
    private Integer RoomQuantity;

    /// <summary>
    /// 最大入住人数
    /// </summary>
    private Integer MaxOccupancy;

    /// <summary>
    /// 最大儿童数
    /// </summary>
    private String MaxChild;

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
    /// 卫浴
    /// <para>1独立卫浴</para>
    /// <para>2公共卫浴</para>
    /// <para>unknow未知</para>
    /// </summary>
    private String BathRoomType;

    /// <summary>
    /// 床型信息
    /// </summary>
    private List<Beds> Beds;

}
