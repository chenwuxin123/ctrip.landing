package com.meipiao.ctrip.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.city.Coordinates;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.vo.Hotel;
import com.meipiao.ctrip.entity.vo.HotelTo;
import com.meipiao.ctrip.entity.vo.HotelVo;
import com.meipiao.ctrip.entity.vo.imge.Image;
import com.meipiao.ctrip.service.CtripHotelService;
import com.meipiao.ctrip.service.CtripPriceService;
import com.meipiao.ctrip.utils.CopyUtil;
import com.meipiao.ctrip.utils.QmisException;

import com.meipiao.ctrip.vo.AppCtripHotelParam;
import com.meipiao.ctrip.vo.CtripHotel;
import com.meipiao.ctrip.vo.CtripHotrlTo;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/6/30 21:21
 */
@Service
public class CtripHotelServiceImpl implements CtripHotelService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CtripPriceService ctripPriceService;

    @Override
    public HotelTo findHotelInfo(HotelVo hotelVo) {
        return null;
    }
}
