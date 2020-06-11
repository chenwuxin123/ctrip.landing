package com.meipiao.ctrip.entity.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/9 11:43
 */
@Data
public class Coordinates implements Serializable {
    private String Provider;
    private String LNG;
    private String LAT;
}
