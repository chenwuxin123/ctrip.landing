package com.meipiao.ctrip.entity.request.book;

import java.time.LocalDateTime;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:14
 */
public class CheckBookReq {
    private AvailRequestSegments AvailRequestSegments;

    private LocalDateTime TimeStamp = LocalDateTime.now();

    private Double Version = 1.0;

    public void setAvailRequestSegments(AvailRequestSegments AvailRequestSegments) {
        this.AvailRequestSegments = AvailRequestSegments;
    }

    public AvailRequestSegments getAvailRequestSegments() {
        return this.AvailRequestSegments;
    }

    public LocalDateTime getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        TimeStamp = timeStamp;
    }

    public Double getVersion() {
        return Version;
    }

    public void setVersion(Double version) {
        Version = version;
    }

    @Override
    public String toString() {
        return "CheckBookReq{" +
                "AvailRequestSegments=" + AvailRequestSegments +
                ", TimeStamp=" + TimeStamp +
                ", Version=" + Version +
                '}';
    }
}
