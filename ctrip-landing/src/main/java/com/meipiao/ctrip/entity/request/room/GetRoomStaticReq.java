package com.meipiao.ctrip.entity.request.room;

import com.meipiao.ctrip.entity.request.page.PagingSettings;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 8:48
 */
@Data
public class GetRoomStaticReq implements Serializable {
    /// <summary>
    /// 获取房型的配置信息
    /// </summary>
    private RoomSearchCandidate SearchCandidate ;

    /// <summary>
    /// 基础房型配置
    /// </summary>
    private RoomSettings Settings ;

    /// <summary>
    /// 
    /// </summary>
    private com.meipiao.ctrip.entity.request.page.PagingSettings PagingSettings ;
}
