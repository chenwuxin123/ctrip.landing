package com.meipiao.ctrip.entity.request.rate;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/8 13:06
 */
public class OrderBy {
    /**
     * 	排序规则的名称。
     *  MinPrice-按起价排序；
     */
    private String OrderName = "MinPrice";
    /**
     * DESC-倒序；
     * ASC-顺序；
     */
    private String OrderType = "ASC";

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }
}
