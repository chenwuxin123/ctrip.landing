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
public class RoomDetail implements Serializable {

    /// <summary>
    /// 酒店id
    /// </summary>
    private Long hotelId;

    /// <summary>
    /// 房型Id
    /// </summary>
    private Long roomId;

    private Long updateTimeStamp;

    /// <summary>
    /// 房型名称
    /// <para></para>行政商务房
    /// </summary>
    private String roomTypeName;

    /// <summary>
    /// 房型英文名称
    /// <para></para>行政商务房
    /// </summary>
    private String roomTypeName_En;

    /// <summary>
    /// 房型归属
    /// <para>标准间</para>
    /// </summary>
    private String standardRoomType;

    /// <summary>
    /// 房间数
    /// </summary>
    private Integer roomQuantity;

    /// <summary>
    /// 最大入住人数
    /// </summary>
    private Integer maxOccupancy;

    /// <summary>
    /// 最大儿童数
    /// </summary>
    private String maxChild;

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
    /// 卫浴
    /// <para>1独立卫浴</para>
    /// <para>2公共卫浴</para>
    /// <para>unknow未知</para>
    /// </summary>
    private String bathRoomType;

    /// <summary>
    /// 床型信息
    /// </summary>
    private List<Beds> beds;

    /// <summary>
    /// 照片
    /// </summary>
    private List<String> pictures;

    //// <summary>
    /// 子房间信息
    /// </summary>
    private List<SubRoomDetail> subRoom;

}
