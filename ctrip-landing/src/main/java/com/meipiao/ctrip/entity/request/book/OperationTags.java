package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:16
 */
public class OperationTags {
    private String Code;

    private String Value;

    public void setCode(String Code){
        this.Code = Code;
    }
    public String getCode(){
        return this.Code;
    }
    public void setValue(String Value){
        this.Value = Value;
    }
    public String getValue(){
        return this.Value;
    }
}
