package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:18
 */
public class RatePlanCandidate {
    private String RoomID;

    private String RatePlanID;

    private String RatePlanCategory;

    private boolean PrepaidIndicator;

    public void setRoomID(String RoomID) {
        this.RoomID = RoomID;
    }

    public String getRoomID() {
        return this.RoomID;
    }

    public void setRatePlanID(String RatePlanID) {
        this.RatePlanID = RatePlanID;
    }

    public String getRatePlanID() {
        return this.RatePlanID;
    }

    public void setRatePlanCategory(String RatePlanCategory) {
        this.RatePlanCategory = RatePlanCategory;
    }

    public String getRatePlanCategory() {
        return this.RatePlanCategory;
    }

    public void setPrepaidIndicator(boolean PrepaidIndicator) {
        this.PrepaidIndicator = PrepaidIndicator;
    }

    public boolean getPrepaidIndicator() {
        return this.PrepaidIndicator;
    }
}
