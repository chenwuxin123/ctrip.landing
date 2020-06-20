package com.meipiao.ctrip.service;

import com.meipiao.ctrip.entity.request.QueryRateBody;
import com.meipiao.ctrip.entity.request.QuetyPolicyBody;
import com.meipiao.ctrip.entity.response.city.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.rate.CancelDetail;
import com.meipiao.ctrip.entity.response.rate.PolicyDetail;
import com.meipiao.ctrip.entity.response.rate.PriceDetail;
import com.meipiao.ctrip.entity.response.room.RoomDetail;
import com.meipiao.ctrip.entity.response.room.SubRoomDetail;
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
            mongoTemplate.upsert(query, update, Destination.class, "Destination");      //有则更新，没有则新增
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
            mongoTemplate.upsert(query, update, HotelIdDetail.class, "HotelIdDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //酒店静态信息入库
    public void updateHotelStatic(HotelDetail hotelIdDetailBean) {
        Query query = new Query();
        query.addCriteria(Criteria.where("MasterHotelNum").is(hotelIdDetailBean.getMasterHotelNum()));//根据酒店id更新
        Update update = new Update();
        update.set("UpdateTimeStamp", System.currentTimeMillis() / 1000);
        update.set("MasterHotelNum", hotelIdDetailBean.getMasterHotelNum());
        update.set("DataFlag", hotelIdDetailBean.getDataFlag());
        update.set("DataFlagRemark", hotelIdDetailBean.getDataFlagRemark());
        update.set("HotelName", hotelIdDetailBean.getHotelName());
        update.set("HotelNameEn", hotelIdDetailBean.getHotelNameEn());
        update.set("AddressEn", hotelIdDetailBean.getAddressEn());
        update.set("StarRating", hotelIdDetailBean.getStarRating());
        update.set("IsOfficialRating", hotelIdDetailBean.getIsOfficialRating());
        update.set("OpenYear", hotelIdDetailBean.getOpenYear());
        update.set("RoomQuantity", hotelIdDetailBean.getRoomQuantity());
        update.set("Address", hotelIdDetailBean.getAddress());
        update.set("coordinates", hotelIdDetailBean.getCoordinates());
        update.set("CountryName", hotelIdDetailBean.getCountryName());
        update.set("ProvinceName", hotelIdDetailBean.getProvinceName());
        update.set("CityName", hotelIdDetailBean.getCityName());
        update.set("PostalCode", hotelIdDetailBean.getPostalCode());
        update.set("Phone", hotelIdDetailBean.getPhone());
        update.set("Fax", hotelIdDetailBean.getFax());
        update.set("Email", hotelIdDetailBean.getEmail());
        mongoTemplate.upsert(query, update, HotelDetail.class, "HotelDetail");      //有则更新，没有则新增
    }

    //物理房型静态信息入库
    public int updateRoomStatic(List<RoomDetail> roomStaticBean) {
        int count = 0;
        for (RoomDetail roomDetail : roomStaticBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("MasterHotelNum").is(roomDetail.getMasterHotelNum()).
                    and("RoomId").is(roomDetail.getRoomId()));  //根据酒店id更新和物理房间id更新
            Update update = new Update();
            update.set("UpdateTimeStamp", roomDetail.getUpdateTimeStamp());
            update.set("MasterHotelNum", roomDetail.getMasterHotelNum());
            update.set("RoomId", roomDetail.getRoomId());
            update.set("RoomTypeName", roomDetail.getRoomTypeName());
            update.set("RoomTypeName_En", roomDetail.getRoomTypeName_En());
            update.set("StandardRoomType", roomDetail.getStandardRoomType());
            update.set("RoomQuantity", roomDetail.getRoomQuantity());
            update.set("MaxOccupancy", roomDetail.getMaxOccupancy());
            update.set("MaxChild", roomDetail.getMaxChild());
            update.set("AreaRange", roomDetail.getAreaRange());
            update.set("FloorRange", roomDetail.getFloorRange());
            update.set("HasWindow", roomDetail.getHasWindow());
            update.set("BathRoomType", roomDetail.getBathRoomType());
            update.set("Beds", roomDetail.getBeds());
            mongoTemplate.upsert(query, update, RoomDetail.class, "RoomDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //子房型静态信息入库
    public int updateSubRoomStatic(List<SubRoomDetail> subRoomStaticBean) {
        int count = 0;
        for (SubRoomDetail subRoomDetail : subRoomStaticBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("MasterHotelNum").is(subRoomDetail.getMasterHotelNum()).
                    and("RoomId").is(subRoomDetail.getRoomId()).
                    and("RoomCode").is(subRoomDetail.getRoomCode()));  //根据酒店id更新和物理房间id子房间id更新
            Update update = new Update();
            update.set("UpdateTimeStamp", subRoomDetail.getUpdateTimeStamp());
            update.set("MasterHotelNum", subRoomDetail.getMasterHotelNum());
            update.set("RoomCode", subRoomDetail.getRoomCode());
            update.set("RoomId", subRoomDetail.getRoomId());
            update.set("RoomName", subRoomDetail.getRoomName());
            update.set("PayType", subRoomDetail.getPayType());
            update.set("RoomQuantity", subRoomDetail.getRoomQuantity());
            update.set("MaxOccupancy", subRoomDetail.getMaxOccupancy());
            update.set("AreaRange", subRoomDetail.getAreaRange());
            update.set("FloorRange", subRoomDetail.getFloorRange());
            update.set("HasWindow", subRoomDetail.getHasWindow());
            update.set("ExtraBedFee", subRoomDetail.getExtraBedFee());
            update.set("IsHourlyRoom", subRoomDetail.getIsHourlyRoom());
            update.set("IsFromAPI", subRoomDetail.getIsFromAPI());
            update.set("IsShowAgencyTag", subRoomDetail.getIsShowAgencyTag());
            update.set("InvoiceType", subRoomDetail.getInvoiceType());
            update.set("InvoiceMode", subRoomDetail.getInvoiceMode());
            update.set("IsSupportSpecialInvoice", subRoomDetail.getIsSupportSpecialInvoice());
            update.set("ReceiveTextRemark", subRoomDetail.getReceiveTextRemark());
            update.set("IsNeedCustomerTelephone", subRoomDetail.getIsNeedCustomerTelephone());
            update.set("IsClosed", subRoomDetail.getIsClosed());
            update.set("IsAllowRepricing", subRoomDetail.getIsAllowRepricing());
            update.set("IsAllowSmoking", subRoomDetail.getIsAllowSmoking());
            update.set("TimeLimitInfos", subRoomDetail.getTimeLimitInfos());
            mongoTemplate.upsert(query, update, SubRoomDetail.class, "SubRoomDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //直连价格入库
    public int updatePriceDetail(List<PriceDetail> priceDetailBean) {
        int count = 0;
        for (PriceDetail priceDetail : priceDetailBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("MasterHotelNum").is(priceDetail.getMasterHotelNum()));
            query.addCriteria(Criteria.where("RoomId").is(priceDetail.getRoomId()));
            query.addCriteria(Criteria.where("RoomCode").is(priceDetail.getRoomCode()));
            query.addCriteria(Criteria.where("UseDay").is(priceDetail.getUseDay()));//根据酒店id更新和物理房间id子房间id可用日期更新
            Update update = new Update();
            update.set("UpdateTimeStamp", priceDetail.getUpdateTimeStamp());
            update.set("MasterHotelNum", priceDetail.getMasterHotelNum());
            update.set("RoomId", priceDetail.getRoomId());
            update.set("RoomCode", priceDetail.getRoomCode());
            update.set("RatePlanId", priceDetail.getRatePlanId());
            update.set("UseDay", priceDetail.getUseDay());
            update.set("RemainingRooms", priceDetail.getRemainingRooms());
            update.set("PromoTionAmout", priceDetail.getPromoTionAmout());
            update.set("PromoTionPercent", priceDetail.getPromoTionPercent());
            update.set("DisplayCurrency", priceDetail.getDisplayCurrency());
            update.set("DisplayCostCurrency", priceDetail.getDisplayCostCurrency());
            update.set("OriginalCurrency", priceDetail.getOriginalCurrency());
            update.set("OriginalCostCurrency", priceDetail.getOriginalCostCurrency());
            update.set("RawPrice", priceDetail.getRawPrice());
            update.set("Currency", priceDetail.getCurrency());
            mongoTemplate.upsert(query, update, PriceDetail.class, "PriceDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //直连政策入库
    public int updatePolicyDetail(List<PolicyDetail> policyDetailBean) {
        int count = 0;
        for (PolicyDetail policyDetail : policyDetailBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("MasterHotelNum").is(policyDetail.getMasterHotelNum()).
                    and("RoomId").is(policyDetail.getRoomId()).
                    and("RoomCode").is(policyDetail.getRoomCode()));  //根据酒店id更新和物理房间id子房间id更新
            Update update = new Update();
            update.set("UpdateTimeStamp", policyDetail.getUpdateTimeStamp());
            update.set("MasterHotelNum", policyDetail.getMasterHotelNum());
            update.set("RoomId", policyDetail.getRoomId());
            update.set("RoomCode", policyDetail.getRoomCode());
            update.set("RatePlanId", policyDetail.getRatePlanId());
            update.set("Breakfast", policyDetail.getBreakfast());
            update.set("Lunch", policyDetail.getLunch());
            update.set("Dinner", policyDetail.getDinner());
            mongoTemplate.upsert(query, update, PolicyDetail.class, "PolicyDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //直连取消规则入库
    public int updateCancelDetail(List<CancelDetail> cancelDetailBean) {
        int count = 0;
        for (CancelDetail cancelDetail : cancelDetailBean) {
            Query query = new Query();
            query.addCriteria(Criteria.where("MasterHotelNum").is(cancelDetail.getMasterHotelNum()).
                    and("RoomId").is(cancelDetail.getRoomId()).
                    and("RoomCode").is(cancelDetail.getRoomCode()));  //根据酒店id更新和物理房间id子房间id更新
            Update update = new Update();
            update.set("UpdateTimeStamp", cancelDetail.getUpdateTimeStamp());
            update.set("MasterHotelNum", cancelDetail.getMasterHotelNum());
            update.set("RoomId", cancelDetail.getRoomId());
            update.set("RoomCode", cancelDetail.getRoomCode());
            update.set("UseDay", cancelDetail.getUseDay());
            update.set("Cancels", cancelDetail.getCancels());
            update.set("Start", cancelDetail.getStart());
            update.set("End", cancelDetail.getEnd());
            mongoTemplate.upsert(query, update, CancelDetail.class, "CancelDetail");      //有则更新，没有则新增
            count++;
        }
        return count;
    }

    //条件搜索直连价格
    public List<PriceDetail> queryPrice(QueryRateBody queryRateBody) {
        Query query = new Query();
        if (queryRateBody.getMasterHotelNum() != null && !queryRateBody.getMasterHotelNum().equals("")) {
            query.addCriteria(Criteria.where("MasterHotelNum").is(queryRateBody.getMasterHotelNum()));
        }
        if (queryRateBody.getRoomId() != null && !queryRateBody.getRoomId().equals("")) {
            query.addCriteria(Criteria.where("RoomId").is(queryRateBody.getRoomId()));
        }
        if (queryRateBody.getRoomCode() != null && !queryRateBody.getRoomCode().equals("")) {
            query.addCriteria(Criteria.where("RoomCode").is(queryRateBody.getRoomCode()));
        }
        if (queryRateBody.getUseDay() != null && !queryRateBody.getUseDay().equals("")) {
            query.addCriteria(Criteria.where("UseDay").is(queryRateBody.getUseDay()));
        }
        return mongoTemplate.find(query, PriceDetail.class, "PriceDetail");
    }

    //条件搜索直连政策
    public List<PolicyDetail> queryPolicy(QuetyPolicyBody quetyPolicyBody) {
        Query query = new Query();
        if (quetyPolicyBody.getMasterHotelNum() != null && !quetyPolicyBody.getMasterHotelNum().equals("")) {
            query.addCriteria(Criteria.where("MasterHotelNum").is(quetyPolicyBody.getMasterHotelNum()));
        }
        if (quetyPolicyBody.getRoomId() != null && !quetyPolicyBody.getRoomId().equals("")) {
            query.addCriteria(Criteria.where("RoomId").is(quetyPolicyBody.getRoomId()));
        }
        if (quetyPolicyBody.getRoomCode() != null && !quetyPolicyBody.getRoomCode().equals("")) {
            query.addCriteria(Criteria.where("RoomCode").is(quetyPolicyBody.getRoomCode()));
        }
        return mongoTemplate.find(query, PolicyDetail.class, "PolicyDetail");
    }

    public List<CancelDetail> queryCancel(QueryRateBody queryRateBody) {
        Query query = new Query();
        if (queryRateBody.getMasterHotelNum() != null && !queryRateBody.getMasterHotelNum().equals("")) {
            query.addCriteria(Criteria.where("MasterHotelNum").is(queryRateBody.getMasterHotelNum()));
        }
        if (queryRateBody.getRoomId() != null && !queryRateBody.getRoomId().equals("")) {
            query.addCriteria(Criteria.where("RoomId").is(queryRateBody.getRoomId()));
        }
        if (queryRateBody.getRoomCode() != null && !queryRateBody.getRoomCode().equals("")) {
            query.addCriteria(Criteria.where("RoomCode").is(queryRateBody.getRoomCode()));
        }
        if (queryRateBody.getUseDay() != null && !queryRateBody.getUseDay().equals("")) {
            query.addCriteria(Criteria.where("UseDay").is(queryRateBody.getUseDay()));
        }
        return mongoTemplate.find(query, CancelDetail.class, "CancelDetail");
    }
}
