package com.meipiao.ctrip.controller;

import com.meipiao.ctrip.common.Results;
import com.meipiao.ctrip.entity.vo.HotelTo;
import com.meipiao.ctrip.entity.vo.HotelVo;
import com.meipiao.ctrip.service.CtripHotelService;
import com.meipiao.ctrip.vo.AppCtripHotelParam;
import com.meipiao.ctrip.vo.CtripHotrlTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description TODO(携程酒店数据app查询预订)
 * @date 2020/6/30 18:55
 */

@Api(value = "携程酒店数据app查询预订(Ctrip HotelSearch)")
@RestController
@RequestMapping("app/api/holHotel/ctrip")
public class AppApiCtripSearchHotelController  {

    /**
     * 日志记录器
     **/

    @Autowired
    private CtripHotelService ctripHotelService;

    @PostMapping("/findHotel")
    @ApiOperation(value = "携程酒店查询")
    public Results<HotelTo> findHotel(@RequestBody HotelVo vo) {

        /*
                TODO(权限认证)
         */
        HotelTo hotelTo = null;
        try {
            hotelTo = ctripHotelService.findHotelInfo(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Results<HotelTo>(hotelTo);
    }

}
