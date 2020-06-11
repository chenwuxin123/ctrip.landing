package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 14:20
 */
@Data
public class TimeLimitInfo {
    private List<DateRestriction> DateRestrictions;
}
