package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 14:20
 */
@Data
public class TimeLimitInfo implements Serializable {
    private List<DateRestriction> DateRestrictions;
}
