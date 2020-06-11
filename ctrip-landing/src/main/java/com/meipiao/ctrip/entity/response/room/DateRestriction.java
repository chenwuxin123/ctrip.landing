package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 14:22
 */
@Data
public class DateRestriction {
    private String Scope;
    private String DataType;
    private String Start;
    private String end;
}
