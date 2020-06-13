package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:37
 */
@Data
public class RateSearchTagsItem implements Serializable {
    /// <summary>
    ///
    /// </summary>
    private String Code ;

    /// <summary>
    ///
    /// </summary>
    private String Name ;

    /// <summary>
    ///
    /// </summary>
    private String Value ;
}
