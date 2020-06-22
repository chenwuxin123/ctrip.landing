package com.meipiao.ctrip.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 政策查询请求体
 * @Author: Chenwx
 * @Date: 2020/6/16 10:36
 */
@Data
@ApiModel(description = "请求体参数")
public class QueryPolicyBody implements Serializable {
    @ApiModelProperty(value = "酒店id", example = "19754999")
    private String masterHotelNum;

    @ApiModelProperty(value = "物理房型id", example = "82549875")
    private String roomId;

    @ApiModelProperty(value = "子房型id", example = "185467176")
    private String roomCode;
}
