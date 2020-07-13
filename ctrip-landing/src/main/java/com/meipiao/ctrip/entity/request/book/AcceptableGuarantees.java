package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:16
 */
public class AcceptableGuarantees {

    private String SendData = "false";

    public void setSendData(String SendData){
        this.SendData = SendData;
    }
    public String getSendData(){
        return this.SendData;
    }
}
