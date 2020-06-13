package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * mongodb聚合查询
 *
 * @Author: Chenwx
 * @Date: 2020/6/11 11:24
 */
@Component
public class MongoAggregationUtil {

    @Autowired
    MongoTemplate mongoTemplate;

    //输入查询的列
    public Aggregation findAllByColumn(String... column) {
        Aggregation aggregation =
                Aggregation.newAggregation(
                        Aggregation.project(column)
                );
        return aggregation;
    }

    //所有酒店的id
    public ArrayList<String> findAllHotelId(String column) {
        Aggregation aggregation = findAllByColumn(column);
        AggregationResults<BasicDBObject> hotelIds = mongoTemplate.aggregate(aggregation, "HotelIdDetail", BasicDBObject.class);
        ArrayList<String> list = new ArrayList<>();
        for (BasicDBObject basicDBObject : hotelIds) {
            String hotelIdJson = basicDBObject.toJson();
            //hotelIdObj是_id+物理房间的id//hotelIdObj是物理房间的id
            JSONObject hotelIdObj = JSONObject.parseObject(hotelIdJson);
            String HotelID = hotelIdObj.getString("HotelId");
            list.add(HotelID);
        }
        return list;
    }
}
