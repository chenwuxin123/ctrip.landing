package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 14:22
 */
@Data
public class DateRestriction implements Serializable {
    private String scope;
    private String dataType;
    private String start;
    private String end;
}
