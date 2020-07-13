package com.meipiao.ctrip.entity.request.rate;

import io.swagger.models.auth.In;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/8 13:04
 */
public class PriceRange {
    private String lowPrice;
    private String highPrice;

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }
}
