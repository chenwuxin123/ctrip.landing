package com.meipiao.ctrip.entity.response.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:03
 */
@Data
public class CancelEntity implements Serializable {
    /// <summary>
    /// 扣除类型
    /// </summary>
    private String Type ;

    /// <summary>
    /// 额度
    /// </summary>
    private String Amount ;

    /// <summary>
    /// 币种
    /// </summary>
    private String Currency ;
}
