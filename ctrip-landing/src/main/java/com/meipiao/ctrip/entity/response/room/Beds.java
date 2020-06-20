package com.meipiao.ctrip.entity.response.room;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/19 16:54
 */
@Data
public class Beds implements Serializable {
    private List<BedInfo> BedInfo;
    private String ID;
    private String Name;
}
