package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/19 16:55
 */
@Data
public class BedInfo implements Serializable {
    private String ID;
    private String Name;
    private String NumberOfBeds;
    private String BedWidth;
}
