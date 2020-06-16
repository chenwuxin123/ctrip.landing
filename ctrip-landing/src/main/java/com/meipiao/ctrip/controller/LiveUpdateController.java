package com.meipiao.ctrip.controller;

import com.meipiao.ctrip.common.R;
import com.meipiao.ctrip.controller.api.StaticDataController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 实时点击 进行更新数据
 *
 * @Author: Chenwx
 * @Date: 2020/6/15 17:13
 */
@Slf4j
@RestController
@RequestMapping("/live")
@SuppressWarnings("unchecked")
@Api(value = "Ctrip Live Update Api", tags = {"人工点击进行单次数据更新"})
public class LiveUpdateController {

    @Autowired
    StaticDataController staticDataController;

    @PostMapping("/rate")
    @ApiOperation(value = "直连价格实时拉取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelIds", value = "酒店id", required = true, example = "1216012"),
            @ApiImplicitParam(name = "start", value = "入住日期", required = true, example = "2020-06-14"),
            @ApiImplicitParam(name = "end", value = "离店日期", required = true, example = "2020-06-14")
    })
    public R liveRate(String hotelIds, String start, String end) {
        List<String> list = new ArrayList<>();
        list.add(hotelIds);
        try {
            staticDataController.queryRate(list, start, end);
            //再调用查询接口

        } catch (InterruptedException e) {
            return R.fail();
        }
        return R.ok();
    }
}
