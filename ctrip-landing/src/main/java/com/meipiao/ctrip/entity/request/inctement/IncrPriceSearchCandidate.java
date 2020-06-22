package com.meipiao.ctrip.entity.request.inctement;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/22 10:35
 */
@Data
public class IncrPriceSearchCandidate implements Serializable {
    /// <summary>
    /// 指定查询时间范围的开始时间。
    /// 从该时间开始，指定时段(Duration)内，若某售卖房型的房价、房量、房态发了变化，则该售卖房型将出现在返回报文中。
    /// </summary>
    private String StartTime;

    /// <summary>
    /// 时间段长度。取值范围为0-300秒。0等同于300秒。
    /// </summary>
    private Integer Duration;

}
