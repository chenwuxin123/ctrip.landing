package com.meipiao.ctrip.entity.request.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:44
 */
@Data
public class PagingSettings implements Serializable {
    /// <summary>
    /// 每页记录数
    /// <para>获取城市最大值5000</para>
    /// <para>获取酒店Id列表最大值5000</para>
    /// <para>获取房型最大值1000</para>
    /// <para>获取价格信息最大值200</para>
    /// </summary>
    public int pageSize;

    /// <summary>
    /// 首次调用，传空。之后，每次传上次调用时返回报文当中的LastRecordID
    /// </summary>
    public String lastRecordID;
}
