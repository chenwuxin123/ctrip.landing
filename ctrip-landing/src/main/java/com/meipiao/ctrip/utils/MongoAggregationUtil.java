package com.meipiao.ctrip.utils;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Component;

/**
 * mongodb聚合查询
 * @Author: Chenwx
 * @Date: 2020/6/11 11:24
 */
@Component
public class MongoAggregationUtil {

    //输入查询的列
    public Aggregation findAllByColumn(String ...column){
        Aggregation aggregation =
                Aggregation.newAggregation(
                        Aggregation.project(column)
                );
        return aggregation;
    }
}
