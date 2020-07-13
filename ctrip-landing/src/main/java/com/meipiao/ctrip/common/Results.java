package com.meipiao.ctrip.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * <b>开发接口响应结果</b>
 * <b>Description: 统一的Json请求结果类，Controller的返回Json请求的结果可使用此类作为返回结果.</b>
 *
 * <b>Author:</b> lh
 * <b>Date:</b> 2017年2月22日 上午10:58:42
 * <b>Copyright:</b> Copyright ©2017 tripsmis.cn. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                    Author                Detail
 *   ----------------------------------------------------------------------
 *   0.1   2017年2月22日 上午10:58:42    lh
 *         new file.
 * </pre>
 */
@ApiModel(value="返回结果类")
public class Results<T> {

    /** 结果代码, 200: 成功, unknown: 未知; 其他 */
    @ApiModelProperty(value="结果代码, 200: 成功, unknown: 未知; 其他" )
    protected String code;

    /** 失败错误信息 */
    @ApiModelProperty(value="失败错误信息" )
    protected String message;

    /** 结果数据 */
    @ApiModelProperty(value="报文内容")
    protected T data;

    public static final String SUCCESS = "200";

    public static final String SUCCESS_MESSAGE = "成功";

    public static final String UNKNOWN = "unknown";



    public Results() {
        super();
    }

    public Results(T data) {
        this(SUCCESS, null, data);
    }
    public Results(String message,T data) {
        this(SUCCESS, message, data);
    }
    public Results(String code, String message) {
        this(code, message, null);
    }

    public Results(String code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

}