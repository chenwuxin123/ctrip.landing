package com.meipiao.ctrip.entity.response.rate;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 9:46
 */
@Data
@Document(collection = "CancelDetail")
public class CancelDetail implements Serializable {
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
    private String RoomCode;

    /// <summary>
    /// 适用日期
    /// </summary>
    private String UseDay;

    /// <summary>
    /// 取消规则信息
    /// </summary>
    private List<CancelEntity> Cancels;

    /// <summary>
    /// 取消政策的生效时间
    /// </summary>
    private String Start;

    /// <summary>
    /// 取消政策的失效时间
    /// </summary>
    private String End;
}
