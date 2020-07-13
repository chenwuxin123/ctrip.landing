package com.meipiao.ctrip.entity.request.book;

import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:16
 */
public class TPA_Extensions {
    private String LateArrivalTime;

    private String DisplayCurrency = "CNY";

    private List<SpecialRequests> SpecialRequests;

    private ShadowRoomInfo ShadowRoomInfo;

    private List<OperationTags> OperationTags;

    private AcceptableGuarantees AcceptableGuarantees;

    public void setLateArrivalTime(String LateArrivalTime) {
        this.LateArrivalTime = LateArrivalTime;
    }

    public String getLateArrivalTime() {
        return this.LateArrivalTime;
    }

    public void setDisplayCurrency(String DisplayCurrency) {
        this.DisplayCurrency = DisplayCurrency;
    }

    public String getDisplayCurrency() {
        return this.DisplayCurrency;
    }

    public void setSpecialRequests(List<SpecialRequests> SpecialRequests) {
        this.SpecialRequests = SpecialRequests;
    }

    public List<SpecialRequests> getSpecialRequests() {
        return this.SpecialRequests;
    }

    public void setShadowRoomInfo(ShadowRoomInfo ShadowRoomInfo) {
        this.ShadowRoomInfo = ShadowRoomInfo;
    }

    public ShadowRoomInfo getShadowRoomInfo() {
        return this.ShadowRoomInfo;
    }

    public void setOperationTags(List<OperationTags> OperationTags) {
        this.OperationTags = OperationTags;
    }

    public List<OperationTags> getOperationTags() {
        return this.OperationTags;
    }

    public void setAcceptableGuarantees(AcceptableGuarantees AcceptableGuarantees) {
        this.AcceptableGuarantees = AcceptableGuarantees;
    }

    public AcceptableGuarantees getAcceptableGuarantees() {
        return this.AcceptableGuarantees;
    }
}
