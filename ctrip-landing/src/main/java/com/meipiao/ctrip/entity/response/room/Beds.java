package com.meipiao.ctrip.entity.response.room;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/19 16:54
 */
@Data
public class Beds implements Serializable {

    private List<BedInfo> bedInfo;
    private String id;
    private String name;
}

