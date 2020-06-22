package com.meipiao.ctrip.entity.request.inctement;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/22 10:38
 */
@Data
public class IncrPriceSettings implements Serializable {
    /// <summary>
    /// 定义返回的数据范围：RoomPrice-仅返回房价增量；RoomStatus-仅返回房态、房量的增量。
    /// 备注：不填写，则返回房价、房量、房态的增量。
    /// </summary>
    private String ShowDataRange;

    /// <summary>
    /// 是否返回增量明细，传T时会在返回体获取到增量的具体分类；F则不返回增量明细
    /// </summary>
    private String IsShowChangeDetails;
}
