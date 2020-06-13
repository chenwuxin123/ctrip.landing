package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:37
 */
@Data
public class RateSettings implements Serializable {
    /// <summary>
    /// 显示的货币
    /// </summary>
    public String DisplayCurrency = "CNY";
    ;

    /// <summary>
    /// 是否显示不可预订
    /// </summary>
    public String IsShowNonBookable = "F";
    ;

    /// <summary>
    /// 语言类型
    /// </summary>
    public String PrimaryLangID = "zh-cn";
}
