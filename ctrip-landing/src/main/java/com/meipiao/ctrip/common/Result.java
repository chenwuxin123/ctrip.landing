package com.meipiao.ctrip.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Chenwx
 * @Date: 2020/4/23 17:18
 */
@Data
@ApiModel("返回的结果体")
public class Result<T> {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回的数据")
    private T data;

    private Result() {
    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("success");
        return r;
    }

    public static Result nullData() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.HALF_SUCCESS);
        r.setMessage("success");
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("error");
        return r;
    }

    public static Result fail() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.FAIL);
        r.setMessage("fail");
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result data(T t) {
        this.setData(t);
        return this;
    }


}

