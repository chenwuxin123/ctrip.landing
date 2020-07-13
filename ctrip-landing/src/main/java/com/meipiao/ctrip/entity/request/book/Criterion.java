package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:15
 */
public class Criterion {
    private HotelRef HotelRef;

    private StayDateRange StayDateRange;

    private RatePlanCandidates RatePlanCandidates;

    private RoomStayCandidates RoomStayCandidates;

    private TPA_Extensions TPA_Extensions;

    public void setHotelRef(HotelRef HotelRef){
        this.HotelRef = HotelRef;
    }
    public HotelRef getHotelRef(){
        return this.HotelRef;
    }
    public void setStayDateRange(StayDateRange StayDateRange){
        this.StayDateRange = StayDateRange;
    }
    public StayDateRange getStayDateRange(){
        return this.StayDateRange;
    }
    public void setRatePlanCandidates(RatePlanCandidates RatePlanCandidates){
        this.RatePlanCandidates = RatePlanCandidates;
    }
    public RatePlanCandidates getRatePlanCandidates(){
        return this.RatePlanCandidates;
    }
    public void setRoomStayCandidates(RoomStayCandidates RoomStayCandidates){
        this.RoomStayCandidates = RoomStayCandidates;
    }
    public RoomStayCandidates getRoomStayCandidates(){
        return this.RoomStayCandidates;
    }
    public void setTPA_Extensions(TPA_Extensions TPA_Extensions){
        this.TPA_Extensions = TPA_Extensions;
    }
    public TPA_Extensions getTPA_Extensions(){
        return this.TPA_Extensions;
    }

}
