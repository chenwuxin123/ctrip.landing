package com.meipiao.ctrip.entity.response.hotel;

import com.meipiao.ctrip.entity.response.city.Coordinates;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/11 9:46
 */
@Document(collection = "HotelDetail")
@Data
public class HotelDetail implements Serializable {
    @org.springframework.data.annotation.Id
    private String Id;
    private long UpdateTimeStamp;
    private String MasterHotelNum;
    private long MainHotelId;
    private long SubHotelId;
    private Integer DataFlag;
    private String DataFlagRemark;
    private String HotelName;
    private String HotelNameEn;
    private String AddressEn;
    private Integer StarRating;
    private Integer IsOfficialRating; //1:true 0:false
    private String OpenYear;
    private Integer RoomQuantity;
    private String Address;
    private List<Coordinates> coordinates;
    private String CountryName;
    private String ProvinceName;
    private String CityName;
    private String PostalCode;
    private String Phone;
    private String Fax;
    private String Email;
}
