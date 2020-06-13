package com.meipiao.ctrip.entity.request.rate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 10:33
 */
@Data
public class RateSearchCandidate implements Serializable {
    /// <summary>
    ///
    /// </summary>
    private String HotelID ;

    /// <summary>
    /// 入离日期
    /// </summary>
    private DateRange DateRange ;

    /// <summary>
    /// 人数
    /// </summary>
    private Occupancy Occupancy ;

    /// <summary>
    ///
    /// </summary>
    private List<RateSearchTagsItem> SearchTags ;
}
