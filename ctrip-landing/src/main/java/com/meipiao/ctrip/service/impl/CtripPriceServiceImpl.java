package com.meipiao.ctrip.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.service.CtripPriceService;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/1 11:45
 */
@Service
public class CtripPriceServiceImpl implements CtripPriceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Double queryLowestPrice(Long masterHotelNum) {
        Aggregation aggregation =
                Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("hotelId").is(masterHotelNum)),
                        Aggregation.project("displayCurrency").andExclude("_id")
                );
        AggregationResults<BasicDBObject> price = mongoTemplate.aggregate(aggregation, "PriceDetail", BasicDBObject.class);

        Double lowPrice = Math.random() * 1000;
        for (BasicDBObject basicDBObject : price) {
            String result = basicDBObject.toJson();
            JSONObject resJson = JSONObject.parseObject(result);
            if (resJson != null) {
                Double getPrice = Double.valueOf(resJson.getInteger("displayCurrency"));
                if (getPrice < lowPrice) {
                    lowPrice = getPrice;
                }
            }
        }
        return lowPrice;
    }

    @Override
    public List<RoomDetail> findRoom(Long masterHotelNum) {
        Query query = new Query(Criteria.where("hotelId").is(masterHotelNum));
        return mongoTemplate.find(query, RoomDetail.class);
    }


}
