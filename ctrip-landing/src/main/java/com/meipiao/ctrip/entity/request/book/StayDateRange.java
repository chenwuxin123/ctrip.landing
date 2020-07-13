package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:18
 */
public class StayDateRange {
    private String Start;

    private String End;

    public void setStart(String Start){
        this.Start = Start;
    }
    public String getStart(){
        return this.Start;
    }
    public void setEnd(String End){
        this.End = End;
    }
    public String getEnd(){
        return this.End;
    }
}
