package com.meipiao.ctrip.service;

import com.meipiao.ctrip.entity.response.city.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
import com.meipiao.ctrip.entity.response.rate.RoomPriceRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Des: 请求携程接口，将返回的数据进行解析，生成响应的实体。 根据不同的条件，如果存在，进行update操作;如果不存在，进行insert操作
 * @Author: Chenwx
 * @Date: 2020/6/15 11:21
 */
@Component
public class MongodbService {
    @Autowired
    MongoTemplate mongoTemplate;

    //城市入库
    public int updateCity(ArrayList<Destination> destinationEntity) {
        int count = 0;
        for (Destination destination : destinationEntity) {
            Query query = new Query();
            query.addCriteria(Criteria.where("CityID").is(destination.getCityID()));//根据城市id更新
            Update update = new Update();
            update.set("CityID", destination.getCityID());
            update.set("CityName", destination.getCityName());
            update.set("CityEnName", destination.getCityEnName());
            update.set("ParentCityID", destination.getParentCityID());
            update.set("ParentCityName", destination.getParentCityName());
            update.set("ParentCityEnName", destination.getParentCityEnName());
            update.set("ProvinceID", destination.getProvinceID());
            update.set("ProvinceName", destination.getProvinceName());
            update.set("ProvinceEnName", destination.getProvinceEnName());
            update.set("CountryID", destination.getCountryID());
            update.set("CountryName", destination.getCountryName());
            update.set("CountryEnName", destination.getCountryEnName());
            update.set("ContinentID", destination.getContinentID());
            update.set("ContinentEnName", destination.getContinentEnName());
            update.set("Coordinates", destination.getCoordinates());
            update.set("ContinentName", destination.getContinentName());
            update.set("UpdateTimeStamp", System.currentTimeMillis() / 1000);
            mongoTemplate.upsert(query, update, Destination.class);      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //酒店清单的入库
    public int updateHotelId(ArrayList<HotelIdDetail> hotelIdDetail) {
        int count = 0;
        for (HotelIdDetail idDetail : hotelIdDetail) {
            Query query = new Query();
            query.addCriteria(Criteria.where("HotelId").is(idDetail.getHotelId()));//根据酒店id更新
            Update update = new Update();
            update.set("CityID", idDetail.getCityID());
            update.set("CityName", idDetail.getCityName());
            update.set("ProvinceID", idDetail.getProvinceID());
            update.set("ProvinceName", idDetail.getProvinceName());
            update.set("HotelId", idDetail.getHotelId());
            mongoTemplate.upsert(query, update, HotelIdDetail.class);      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //酒店静态信息入库
    public void updateHotelStatic(HotelDetail hotelIdDetailBean) {
        Query query = new Query();
        query.addCriteria(Criteria.where("hotelId").is(hotelIdDetailBean.getHotelId()));//根据酒店id更新
        Update update = new Update();
        update.set("hotelId", hotelIdDetailBean.getHotelId());
        update.set("dataFlag", hotelIdDetailBean.getDataFlag());
        update.set("hotelName", hotelIdDetailBean.getHotelName());
        update.set("hotelEngName", hotelIdDetailBean.getHotelEngName());
        update.set("address", hotelIdDetailBean.getAddress());
        update.set("addressEn", hotelIdDetailBean.getAddressEn());
        update.set("hotelIntroduce", hotelIdDetailBean.getHotelIntroduce());
        update.set("telephone", hotelIdDetailBean.getTelephone());
        update.set("postCode", hotelIdDetailBean.getPostCode());
        update.set("hotelStar", hotelIdDetailBean.getHotelStar());
        update.set("praciceDate", hotelIdDetailBean.getPraciceDate());
        update.set("roomNum", hotelIdDetailBean.getRoomNum());
        update.set("pictures", hotelIdDetailBean.getPictures());
        update.set("businessName", hotelIdDetailBean.getBusinessName());
        update.set("hotelFacilitys", hotelIdDetailBean.getHotelFacilitys());
        update.set("hotelPolicys", hotelIdDetailBean.getHotelPolicys());
        update.set("isOfficialRating", hotelIdDetailBean.getIsOfficialRating());
        update.set("coordinates", hotelIdDetailBean.getCoordinates());
        update.set("countryName", hotelIdDetailBean.getCountryName());
        update.set("provinceName", hotelIdDetailBean.getProvinceName());
        update.set("fax", hotelIdDetailBean.getFax());
        update.set("email", hotelIdDetailBean.getEmail());
        update.set("cityName", hotelIdDetailBean.getCityName());
        update.set("transportationInfos", hotelIdDetailBean.getTransportationInfos());

        mongoTemplate.upsert(query, update, HotelDetail.class);      //有则更新，没有则新增
    }

    //物理房型静态信息入库
    public void updateRoomStatic(List<RoomDetail> roomStaticBean) {

        for (RoomDetail roomDetail : roomStaticBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("hotelId").is(roomDetail.getHotelId()).
                    and("roomId").is(roomDetail.getRoomId()));  //根据酒店id更新和物理房间id更新
            Update update = new Update();
            update.set("updateTimeStamp", roomDetail.getUpdateTimeStamp());
            update.set("hotelId", roomDetail.getHotelId());
            update.set("roomId", roomDetail.getRoomId());
            update.set("roomTypeName", roomDetail.getRoomTypeName());
            update.set("roomTypeName_En", roomDetail.getRoomTypeName_En());
            update.set("standardRoomType", roomDetail.getStandardRoomType());
            update.set("roomQuantity", roomDetail.getRoomQuantity());
            update.set("maxOccupancy", roomDetail.getMaxOccupancy());
            update.set("maxChild", roomDetail.getMaxChild());
            update.set("areaRange", roomDetail.getAreaRange());
            update.set("floorRange", roomDetail.getFloorRange());
            update.set("hasWindow", roomDetail.getHasWindow());
            update.set("bathRoomType", roomDetail.getBathRoomType());
            update.set("beds", roomDetail.getBeds());
            update.set("pictures", roomDetail.getPictures());
            update.set("subRoom", roomDetail.getSubRoom());
            mongoTemplate.upsert(query, update, RoomDetail.class);//有则更新，没有则新增
        }
    }

    //子房型静态信息入库
    public int updateSubRoomStatic(List<SubRoomDetail> subRoomStaticBean) {
        int count = 0;
        for (SubRoomDetail subRoomDetail : subRoomStaticBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("hotelId").is(subRoomDetail.getHotelId()).
                    and("roomId").is(subRoomDetail.getRoomId()).
                    and("roomCode").is(subRoomDetail.getRoomCode()));  //根据酒店id更新和物理房间id子房间id更新
            Update update = new Update();
            update.set("updateTimeStamp", subRoomDetail.getUpdateTimeStamp());
            update.set("hotelId", subRoomDetail.getHotelId());
            update.set("roomCode", subRoomDetail.getRoomCode());
            update.set("roomId", subRoomDetail.getRoomId());
            update.set("roomName", subRoomDetail.getRoomName());
            update.set("payType", subRoomDetail.getPayType());
            update.set("roomQuantity", subRoomDetail.getRoomQuantity());
            update.set("maxOccupancy", subRoomDetail.getMaxOccupancy());
            update.set("areaRange", subRoomDetail.getAreaRange());
            update.set("floorRange", subRoomDetail.getFloorRange());
            update.set("hasWindow", subRoomDetail.getHasWindow());
            update.set("extraBedFee", subRoomDetail.getExtraBedFee());
            update.set("isHourlyRoom", subRoomDetail.getIsHourlyRoom());
            update.set("isFromAPI", subRoomDetail.getIsFromAPI());
            update.set("isShowAgencyTag", subRoomDetail.getIsShowAgencyTag());
            update.set("invoiceType", subRoomDetail.getInvoiceType());
            update.set("invoiceMode", subRoomDetail.getInvoiceMode());
            update.set("isSupportSpecialInvoice", subRoomDetail.getIsSupportSpecialInvoice());
            update.set("receiveTextRemark", subRoomDetail.getReceiveTextRemark());
            update.set("isNeedCustomerTelephone", subRoomDetail.getIsNeedCustomerTelephone());
            update.set("isClosed", subRoomDetail.getIsClosed());
            update.set("isAllowRepricing", subRoomDetail.getIsAllowRepricing());
            update.set("isAllowSmoking", subRoomDetail.getIsAllowSmoking());
            update.set("timeLimitInfos", subRoomDetail.getTimeLimitInfos());
            mongoTemplate.upsert(query, update, SubRoomDetail.class);      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //直连价格入库
    public void updatePriceDetail(List<RoomPriceRes> roomPriceResBean) {
        for (RoomPriceRes roomPriceRes : roomPriceResBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("hotelId").is(roomPriceRes.getHotelId()));
            query.addCriteria(Criteria.where("roomId").is(roomPriceRes.getRoomId()));
            query.addCriteria(Criteria.where("roomCode").is(roomPriceRes.getRoomCode()));
            Update update = new Update();
            update.set("hotelId", roomPriceRes.getHotelId());
            update.set("roomId", roomPriceRes.getRoomId());
            update.set("roomCode", roomPriceRes.getRoomCode());
            update.set("roomName", roomPriceRes.getRoomCode());
            update.set("start", roomPriceRes.getStart());
            update.set("end", roomPriceRes.getEnd());
            update.set("latestReserveTime", roomPriceRes.getLatestReserveTime());
            update.set("totalPrice", roomPriceRes.getTotalPrice());
            update.set("payType", roomPriceRes.getPayType());
            update.set("canReserve", roomPriceRes.getCanReserve());
            update.set("guarantee", roomPriceRes.getGuarantee());
            update.set("remainingRooms", roomPriceRes.getRemainingRooms());
            update.set("promotion", roomPriceRes.getPromotion());
            update.set("dailyPrices", roomPriceRes.getDailyPrices());
            mongoTemplate.upsert(query, update, RoomPriceRes.class);
        }
    }
}
