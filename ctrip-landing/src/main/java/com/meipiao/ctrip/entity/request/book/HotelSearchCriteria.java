package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:15
 */
public class HotelSearchCriteria {
    private Criterion Criterion;

    public void setCriterion(Criterion Criterion){
        this.Criterion = Criterion;
    }
    public Criterion getCriterion(){
        return this.Criterion;
    }
}
