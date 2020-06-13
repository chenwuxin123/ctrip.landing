package com.meipiao.ctrip.entity.request.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 16:52
 */
@Data
public class SearchByCityID implements Serializable {
    private String CityID;
}
