package com.meipiao.ctrip.controller.api;

import com.meipiao.ctrip.common.Result;
import com.meipiao.ctrip.entity.request.QueryRateBody;
import com.meipiao.ctrip.entity.request.QueryPolicyBody;
import com.meipiao.ctrip.entity.response.rate.CancelDetail;
import com.meipiao.ctrip.entity.response.rate.PolicyDetail;
import com.meipiao.ctrip.entity.response.rate.PriceDetail;
import com.meipiao.ctrip.service.MongodbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 展示mongodb数据
 *
 * @Author: Chenwx
 * @Date: 2020/6/15 17:11
 */
@Slf4j
@RestController
@RequestMapping("/query")
@SuppressWarnings("unchecked")
@Api(value = "Ctrip Query Data API", tags = {"查询接口"})
public class DisplayDataController {
    @Autowired
    MongodbService mongodbService;

    @PostMapping("/price")
    @ApiOperation(value = "条件搜索直连价格")
    public Result queryPrice(@RequestBody QueryRateBody queryRateBody) {
        List<PriceDetail> priceDetails = mongodbService.queryPrice(queryRateBody);
        log.info("此次共查询到{}条数据", priceDetails.size());
        if (priceDetails.size() > 0) {
            ArrayList<Object> list = new ArrayList<>();
            for (PriceDetail priceDetail : priceDetails) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("priceDetail", priceDetail);
                list.add(map);
            }
            return Result.ok().data(list);
        }
        return Result.nullData();
    }

    @PostMapping("/policy")
    @ApiOperation(value = "条件搜索直连政策")
    public Result queryPolicy(@RequestBody QueryPolicyBody quetyPolicyBody) {
        List<PolicyDetail> policyDetails = mongodbService.queryPolicy(quetyPolicyBody);
        log.info("此次共查询到{}条数据", policyDetails.size());
        if (policyDetails.size() > 0) {
            ArrayList<Object> list = new ArrayList<>();
            for (PolicyDetail policyDetail : policyDetails) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("policyDetail", policyDetail);
                list.add(map);
            }
            return Result.ok().data(list);
        }
        return Result.nullData();
    }

    @PostMapping("/cancel")
    @ApiOperation(value = "条件搜索直连取消规则")
    public Result queryPolicy(@RequestBody QueryRateBody queryRateBody) {
        List<CancelDetail> cancelDetails = mongodbService.queryCancel(queryRateBody);
        log.info("此次共查询到{}条数据", cancelDetails.size());
        if (cancelDetails.size() > 0) {
            ArrayList<Object> list = new ArrayList<>();
            for (CancelDetail cancelDetail : cancelDetails) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("cancelDetail", cancelDetail);
                list.add(map);
            }
            return Result.ok().data(list);
        }
        return Result.nullData();
    }
}
