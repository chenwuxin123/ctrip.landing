package com.meipiao.ctrip.entity.request.book;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/9 13:17
 */
public class RoomStayCandidate {
    private String BookingCode;

    private GuestCounts GuestCounts;

    private int Quantity;

    public void setBookingCode(String BookingCode){
        this.BookingCode = BookingCode;
    }
    public String getBookingCode(){
        return this.BookingCode;
    }
    public void setGuestCounts(GuestCounts GuestCounts){
        this.GuestCounts = GuestCounts;
    }
    public GuestCounts getGuestCounts(){
        return this.GuestCounts;
    }
    public void setQuantity(int Quantity){
        this.Quantity = Quantity;
    }
    public int getQuantity(){
        return this.Quantity;
    }
}
