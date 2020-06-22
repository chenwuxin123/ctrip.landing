package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.city.Coordinates;
import com.meipiao.ctrip.entity.response.city.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.rate.CancelDetail;
import com.meipiao.ctrip.entity.response.rate.CancelEntity;
import com.meipiao.ctrip.entity.response.rate.PolicyDetail;
import com.meipiao.ctrip.entity.response.rate.PriceDetail;
import com.meipiao.ctrip.entity.response.room.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 将相应结果转化成相应的Bean
 *
 * @Author: Chenwx
 * @Date: 2020/6/10 10:54
 */
public class ResponseToBeanUtil {
    public static void main(String[] args) {
        String result = "{\"ResponseStatus\":{\"Timestamp\":\"2020-06-22T11:10:36.423+08:00\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[]}}";
        String lastRecordID = getLastRecordID(result);
        System.out.println(lastRecordID);
    }

    public static String getResponseStatus(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取状态码
        JSONObject responseStatus = JSONObject.parseObject(obj.getString("ResponseStatus"));
        return responseStatus.getString("Ack");
    }


    public static String getLastRecordID(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取LastRecordID
        JSONObject pagingInfo = JSONObject.parseObject(obj.getString("PagingInfo"));
        if (pagingInfo == null) {
            return "";
        }
        return pagingInfo.getString("LastRecordID");
    }

    //全量城市
    public static ArrayList<Destination> getDestinationBean(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        ArrayList<Destination> destinationList = new ArrayList<>();

        JSONObject cityInfos = JSONObject.parseObject(obj.getString("CityInfos"));
        JSONArray cityInfo = cityInfos.getJSONArray("CityInfo");
        for (Object city : cityInfo) {
            Destination destination = new Destination();
            //内层数据
            Coordinates coordinates = new Coordinates();
            String bean = city.toString();
            //jsonBean->CityInfo
            JSONObject jsonBean = JSONObject.parseObject(bean);
            //cds->Coordinates
            JSONArray cdsList = jsonBean.getJSONArray("Coordinates");
            for (Object cds : cdsList) {
                JSONObject cdsJson = JSONObject.parseObject(cds.toString());
                //Coordinates实体数据添加
                coordinates.setProvider(cdsJson.getString("Provider"));
                coordinates.setLAT(cdsJson.getString("LNG"));
                coordinates.setLNG(cdsJson.getString("LAT"));
            }

            //将Coordinates实体添加至Destination实体
            ArrayList<Coordinates> list = new ArrayList<>();
            list.add(coordinates);
            destination.setCoordinates(list);
            destination.setCityID(jsonBean.getString("CityID"));
            destination.setCityName(jsonBean.getString("CityName"));
            destination.setCityEnName(jsonBean.getString("CityEnName"));
            destination.setParentCityID(jsonBean.getString("ParentCityID"));
            destination.setParentCityName(jsonBean.getString("ParentCityName"));
            destination.setParentCityEnName(jsonBean.getString("ParentCityEnName"));
            destination.setProvinceID(jsonBean.getString("ProvinceID"));
            destination.setProvinceName(jsonBean.getString("ProvinceName"));
            destination.setProvinceEnName(jsonBean.getString("ProvinceEnName"));
            destination.setCountryID(jsonBean.getString("CountryID"));
            destination.setCountryName(jsonBean.getString("CountryName"));
            destination.setCountryEnName(jsonBean.getString("CountryEnName"));
            destination.setContinentID(jsonBean.getString("ContinentID"));
            destination.setContinentName(jsonBean.getString("ContinentName"));
            destination.setContinentEnName(jsonBean.getString("ContinentEnName"));
            destinationList.add(destination);
        }
        return destinationList;
    }

    //城市酒店清单
    public static ArrayList<HotelIdDetail> getHotelIdDetailBean(String result, JSONObject cityObj) {
        JSONObject jsonBean = JSONObject.parseObject(result);
        ArrayList<HotelIdDetail> hotelIdDetailList = new ArrayList<>();
        List<String> hotelIDs = (List) jsonBean.get("HotelIDs");
        for (String hotelID : hotelIDs) {
            HotelIdDetail hotelBean = new HotelIdDetail();
            hotelBean.setHotelId(hotelID);
            hotelBean.setCityID(cityObj.getString("CityID"));
            hotelBean.setCityName(cityObj.getString("CityName"));
            hotelBean.setCityName(cityObj.getString("CityName"));
            hotelBean.setProvinceID(cityObj.getString("ProvinceID"));
            hotelBean.setProvinceName(cityObj.getString("ProvinceName"));
            hotelIdDetailList.add(hotelBean);
        }
        return hotelIdDetailList;
    }

    //酒店静态信息
    public static HotelDetail getHotelIdDetailBean(String result) {
        JSONObject hotelStaticInfo = JSONObject.parseObject(result).getJSONObject("HotelStaticInfo");
        String hotelNameEN = "";
        String hotelAddressEN = "";
        String masterHotelNum = hotelStaticInfo.getString("HotelID");
        String hotelName = hotelStaticInfo.getString("HotelName");
        JSONArray hotelTags = hotelStaticInfo.getJSONArray("HotelTags");
        //酒店和地址英文名称
        for (Object hotelTag : hotelTags) {
            JSONObject hotelTagsJson = JSONObject.parseObject(hotelTag.toString());
            if ("HotelNameEN".equals(hotelTagsJson.getString("Name"))) {
                //new 对象 到时候直接set
                hotelNameEN = hotelTagsJson.getString("Value");
            } else if ("HotelAddressEN".equals(hotelTagsJson.getString("Name"))) {
                hotelAddressEN = hotelTagsJson.getString("Value");
            }
        }
        String hotelApplicability = hotelStaticInfo.getJSONObject("ApplicabilityInfo").getString("HotelApplicability");//酒店是否有(3,仅接待大陆客人;4,仅接待大陆和港澳台客人;5,接待大陆、港澳台及外国客人)
        Integer starRating = hotelStaticInfo.getInteger("StarRating"); //星级
        Boolean isOfficialRating = hotelStaticInfo.getBoolean("IsOfficialRating");//标明星级是否有政府机构评定
        String openYear = hotelStaticInfo.getString("OpenYear");
        Integer roomQuantity = hotelStaticInfo.getInteger("RoomQuantity");//酒店的客房数量
        String telephone = hotelStaticInfo.getJSONObject("ContactInfo").getString("Telephone");//电话
        String fax = hotelStaticInfo.getJSONObject("ContactInfo").getString("Fax");//传真
        //GeoInfo
        JSONObject geoInfo = hotelStaticInfo.getJSONObject("GeoInfo");
        String address = geoInfo.getString("Address");//地址
        String postalCode = geoInfo.getString("PostalCode");//酒店所在的城市区域代码
        String country = geoInfo.getJSONObject("Country").getString("Name");//国家
        String province = geoInfo.getJSONObject("Province").getString("Name");//省份
        String city = geoInfo.getJSONObject("City").getString("Name");//城市

        ArrayList<Coordinates> coordinatesWays = new ArrayList<>();
        JSONArray coordinates = geoInfo.getJSONArray("Coordinates");
        for (Object coordinate : coordinates) {
            Coordinates cds = new Coordinates();
            String provider = JSONObject.parseObject(coordinate.toString()).getString("Provider");//经纬度提供者
            String lng = JSONObject.parseObject(coordinate.toString()).getString("LNG");//经纬
            String lat = JSONObject.parseObject(coordinate.toString()).getString("LAT");//经纬
            cds.setProvider(provider);
            cds.setLNG(lng);
            cds.setLAT(lat);
            coordinatesWays.add(cds);
        }

        HotelDetail hotelDetailBean = new HotelDetail();
        //set
        hotelDetailBean.setUpdateTimeStamp(System.currentTimeMillis());
        hotelDetailBean.setMasterHotelNum(masterHotelNum);
        hotelDetailBean.setDataFlag("3".equals(hotelApplicability) ? 1 : 0);
        hotelDetailBean.setHotelName(hotelName);
        if (!"".equals(hotelNameEN)) {
            hotelDetailBean.setHotelNameEn(hotelNameEN);
        }
        if (!"".equals(hotelAddressEN)) {
            hotelDetailBean.setAddressEn(hotelAddressEN);
        }
        hotelDetailBean.setStarRating(starRating);
        hotelDetailBean.setIsOfficialRating(isOfficialRating ? 1 : 0);
        hotelDetailBean.setOpenYear(openYear);
        hotelDetailBean.setRoomQuantity(roomQuantity);
        hotelDetailBean.setAddress(address);
        hotelDetailBean.setCoordinates(coordinatesWays);
        hotelDetailBean.setCountryName(country);
        hotelDetailBean.setProvinceName(province);
        hotelDetailBean.setCityName(city);
        hotelDetailBean.setPostalCode(postalCode);
        hotelDetailBean.setPhone(telephone);
        hotelDetailBean.setFax(fax);
        return hotelDetailBean;
    }

    //房型静态信息(物理房型)
    public static List<RoomDetail> getRoomStaticBean(String result, String masterHotelNum) {

        ArrayList<RoomDetail> list = new ArrayList<>();

        JSONArray roomStaticInfos = JSONObject.parseObject(result).getJSONArray("RoomStaticInfos");
        for (Object roomStaticInfo : roomStaticInfos) {
            //先解析RoomTypeInfo
            String roomTypeInfo = JSONObject.parseObject(roomStaticInfo.toString()).getString("RoomTypeInfo");
            JSONObject jsonRoomType = JSONObject.parseObject(roomTypeInfo);
            String roomId = jsonRoomType.getString("RoomTypeID");
            String roomTypeName = jsonRoomType.getString("RoomTypeName");
            //RoomTypeName_En
            String standardRoomType = jsonRoomType.getString("StandardRoomType");
            Integer roomQuantity = jsonRoomType.getInteger("RoomQuantity");
            Integer maxOccupancy = jsonRoomType.getInteger("MaxOccupancy");
            String areaRange = jsonRoomType.getString("AreaRange");
            String floorRange = jsonRoomType.getString("FloorRange");
            Integer hasWindow = jsonRoomType.getInteger("HasWindow");
            String bathRoomType = jsonRoomType.getString("BathRoomType");
            //MaxChild
            String maxChild = jsonRoomType.getJSONObject("ChildLimit").getString("MaxOccupancy");
            //Beds
            JSONArray beaArray = jsonRoomType.getJSONArray("RoomBedInfos");
            //床的信息
            ArrayList<Beds> bedList = new ArrayList<>();
            for (Object bea : beaArray) {
                JSONObject bed = JSONObject.parseObject(bea.toString());
                //当床型分类ID为多床（ID=363）时，BedInfo数组中多个床型之间的关系为“且”；当床型分类ID非多床（ID!=363）时，BedInfo数组中多个床型之间的关系为“或”。
                String badId = bed.getString("ID");
                String bedName = bed.getString("Name");//床型名称(外层)
                JSONArray bedInfoJson = bed.getJSONArray("BedInfo");
                ArrayList<BedInfo> bedInfosList = new ArrayList<>();
                for (Object bedInfos : bedInfoJson) {
                    JSONObject bedInfo = JSONObject.parseObject(bedInfos.toString());
                    BedInfo bedInfoBean = new BedInfo();
                    String bedInfoId = bedInfo.getString("ID");//具体床id
                    String bedInfoName = bedInfo.getString("Name");//具体床名
                    String numberOfBeds = bedInfo.getString("NumberOfBeds");
                    String bedWidth = bedInfo.getString("BedWidth");
                    bedInfoBean.setID(bedInfoId);
                    bedInfoBean.setName(bedInfoName);
                    bedInfoBean.setNumberOfBeds(numberOfBeds);
                    bedInfoBean.setBedWidth(bedWidth);
                    bedInfosList.add(bedInfoBean);
                }
                Beds beds = new Beds();
                beds.setID(badId);
                beds.setName(bedName);
                beds.setBedInfo(bedInfosList);
                bedList.add(beds);
            }
            RoomDetail roomDetail = new RoomDetail();
            roomDetail.setUpdateTimeStamp(System.currentTimeMillis());
            roomDetail.setMasterHotelNum(masterHotelNum);
            roomDetail.setRoomId(roomId);
            roomDetail.setRoomTypeName(roomTypeName);
            roomDetail.setStandardRoomType(standardRoomType);
            roomDetail.setRoomQuantity(roomQuantity);
            roomDetail.setMaxOccupancy(maxOccupancy);
            roomDetail.setMaxChild(maxChild);
            roomDetail.setAreaRange(areaRange);
            roomDetail.setFloorRange(floorRange);
            roomDetail.setHasWindow(hasWindow);
            roomDetail.setBathRoomType(bathRoomType);
            roomDetail.setBeds(bedList);
            list.add(roomDetail);
        }
        return list;
    }

    //房型静态信息(子房型)
    public static List<SubRoomDetail> getSubRoomStaticBean(String result, String masterHotelNum) {

        JSONArray roomStaticInfos = JSONObject.parseObject(result).getJSONArray("RoomStaticInfos");
        ArrayList<SubRoomDetail> subList = new ArrayList<>();
        for (Object roomStaticInfo : roomStaticInfos) {
            //获取物理房型的id
            String roomTypeInfo = JSONObject.parseObject(roomStaticInfo.toString()).getString("RoomTypeInfo");
            JSONObject jsonRoomType = JSONObject.parseObject(roomTypeInfo);
            String roomId = jsonRoomType.getString("RoomTypeID");//物理房型ID
            //解析RoomInfos
            JSONArray roomInfos = JSONObject.parseObject(roomStaticInfo.toString()).getJSONArray("RoomInfos");
            for (Object roomInfo : roomInfos) {
                JSONObject subRoomJson = JSONObject.parseObject(roomInfo.toString());
                String subRoomId = subRoomJson.getString("RoomID");//子房型id
                String roomName = subRoomJson.getString("RoomName");
                String payType = subRoomJson.getString("PayType");
                Integer roomQuantity = subRoomJson.getInteger("RoomQuantity");
                Integer maxOccupancy = subRoomJson.getInteger("MaxOccupancy");
                String areaRange = subRoomJson.getString("AreaRange");
                String floorRange = subRoomJson.getString("FloorRange");
                Integer hasWindow = subRoomJson.getInteger("HasWindow");
                String extraBedFee = subRoomJson.getString("ExtraBedFee");
                Boolean isHourlyRoom = subRoomJson.getBoolean("IsHourlyRoom");
                Boolean isFromAPI = subRoomJson.getBoolean("IsFromAPI");
                Boolean isShowAgencyTag = subRoomJson.getBoolean("IsShowAgencyTag");
                Integer invoiceType = subRoomJson.getInteger("InvoiceType");
                String invoiceMode = subRoomJson.getString("InvoiceMode");
                String isSupportSpecialInvoice = subRoomJson.getString("IsSupportSpecialInvoice");
                Boolean receiveTextRemark = subRoomJson.getBoolean("ReceiveTextRemark");
                String isNeedCustomerTelephone = subRoomJson.getString("IsNeedCustomerTelephone");
                String isAllowRepricing = subRoomJson.getString("IsAllowRepricing");
                String isClosed = subRoomJson.getString("IsClosed");
                String isAllowSmoking = JSONObject.parseObject(subRoomJson.getString("Smoking")).getString("IsAllowSmoking");

                //添加实体属性
                SubRoomDetail subRoomDetail = new SubRoomDetail();
                subRoomDetail.setUpdateTimeStamp(System.currentTimeMillis());
                subRoomDetail.setMasterHotelNum(masterHotelNum);
                subRoomDetail.setRoomCode(subRoomId);
                subRoomDetail.setRoomId(roomId);
                subRoomDetail.setRoomName(roomName);
                subRoomDetail.setPayType(payType);
                subRoomDetail.setRoomQuantity(roomQuantity);
                subRoomDetail.setMaxOccupancy(maxOccupancy);
                subRoomDetail.setAreaRange(areaRange);
                subRoomDetail.setFloorRange(floorRange);
                subRoomDetail.setHasWindow(hasWindow);
                subRoomDetail.setExtraBedFee(extraBedFee);
                subRoomDetail.setIsHourlyRoom(isHourlyRoom ? "true" : "false");
                subRoomDetail.setIsFromAPI(isFromAPI ? "true" : "false");
                subRoomDetail.setIsShowAgencyTag(isShowAgencyTag ? "true" : "false");
                subRoomDetail.setInvoiceType(invoiceType);
                subRoomDetail.setInvoiceMode(invoiceMode);
                subRoomDetail.setIsSupportSpecialInvoice(isSupportSpecialInvoice);
                subRoomDetail.setReceiveTextRemark(receiveTextRemark ? "true" : "false");
                subRoomDetail.setIsNeedCustomerTelephone(isNeedCustomerTelephone);
                subRoomDetail.setIsClosed(isClosed);
                subRoomDetail.setIsAllowRepricing(isAllowRepricing);
                subRoomDetail.setIsAllowSmoking(isAllowSmoking);

                //BookingRules
                JSONArray bookingRules = subRoomJson.getJSONArray("BookingRules");
                ArrayList<TimeLimitInfo> tliList = new ArrayList<>();
                for (Object bookingRule : bookingRules) {
                    JSONArray timeLimitInfos = JSONObject.parseObject(bookingRule.toString()).getJSONArray("TimeLimitInfo");
                    //第二层list 放TimeLimitInfo
                    TimeLimitInfo tli = new TimeLimitInfo();
                    for (Object timeLimitInfo : timeLimitInfos) {
                        //第一层list 放DateRestriction
                        ArrayList<DateRestriction> drcList = new ArrayList<>();
                        JSONArray dateRestrictions = JSONObject.parseObject(timeLimitInfo.toString()).getJSONArray("DateRestrictions");
                        for (Object dateRestriction : dateRestrictions) {
                            DateRestriction drc = new DateRestriction();
                            String scope = JSONObject.parseObject(dateRestriction.toString()).getString("Scope");
                            System.err.println("获取的值" + scope);
                            String dateType = JSONObject.parseObject(dateRestriction.toString()).getString("DateType");
                            String start = JSONObject.parseObject(dateRestriction.toString()).getString("Start");
                            String end = JSONObject.parseObject(dateRestriction.toString()).getString("End");
                            drc.setDataType(dateType);
                            drc.setScope(scope);
                            drc.setStart(start);
                            drc.setEnd(end);
                            drcList.add(drc);
                        }
                        tli.setDateRestrictions(drcList);
                    }
                    tliList.add(tli);
                }
                subRoomDetail.setTimeLimitInfos(tliList);
                subList.add(subRoomDetail);
            }
        }
        return subList;
    }

    //直连政策
    public static List<PolicyDetail> getPolicyDetailBean(String result, String masterHotelNum) {
        ArrayList<PolicyDetail> pdList = new ArrayList<>();
        JSONArray roomPriceItems = JSONObject.parseObject(result).getJSONArray("RoomPriceItems");
        for (Object roomPriceItem : roomPriceItems) {
            JSONObject itemJson = JSONObject.parseObject(roomPriceItem.toString());
            String roomId = itemJson.getString("RoomTypeID");//物理房型id
            JSONArray roomPriceInfos = itemJson.getJSONArray("RoomPriceInfos");
            for (Object roomPriceInfo : roomPriceInfos) {
                JSONObject roomPriceJson = JSONObject.parseObject(roomPriceInfo.toString());
                String roomCode = roomPriceJson.getString("RoomID");//子房型id
                JSONArray priceInfos = roomPriceJson.getJSONArray("PriceInfos");

                for (Object priceInfo : priceInfos) {
                    JSONObject priceInfoJson = JSONObject.parseObject(priceInfo.toString());
                    JSONArray dailyPrices = priceInfoJson.getJSONArray("DailyPrices");
                    for (Object dailyPrice : dailyPrices) {
                        JSONObject dailyPriceJson = JSONObject.parseObject(dailyPrice.toString());
                        JSONObject mealInfo = JSONObject.parseObject(dailyPriceJson.getString("MealInfo"));
                        Integer breakfast = mealInfo.getInteger("NumberOfBreakfast");
                        Integer lunch = mealInfo.getInteger("NumberOfLunch");
                        Integer dinner = mealInfo.getInteger("NumberOfDinner");
                        PolicyDetail policyDetail = new PolicyDetail();
                        policyDetail.setUpdateTimeStamp(System.currentTimeMillis() / 1000);
                        policyDetail.setBreakfast(breakfast);//早
                        policyDetail.setLunch(lunch);//中
                        policyDetail.setDinner(dinner);//晚
                        policyDetail.setMasterHotelNum(masterHotelNum);
                        policyDetail.setRoomId(roomId);//物理房型id
                        policyDetail.setRoomCode(roomCode);//子房型id
                        policyDetail.setRatePlanId(MD5.getMD5(roomId + roomCode + breakfast));// 政策Id（md5-Key）RoomId+RoomCode+Breakfast
                        pdList.add(policyDetail);
                    }
                }

            }
        }
        return pdList;
    }

    //直连价格
    public static List<PriceDetail> getPriceDetailBean(String result, String masterHotelNum) {
        ArrayList<PriceDetail> pdList = new ArrayList<>();
        JSONArray roomPriceItems = JSONObject.parseObject(result).getJSONArray("RoomPriceItems");
        //RoomPriceItems
        for (Object roomPriceItem : roomPriceItems) {
            JSONObject itemJson = JSONObject.parseObject(roomPriceItem.toString());
            String roomId = itemJson.getString("RoomTypeID");//物理房型id
            JSONArray roomPriceInfos = itemJson.getJSONArray("RoomPriceInfos");
            //RoomPriceInfos
            for (Object roomPriceInfo : roomPriceInfos) {
                JSONObject roomPriceJson = JSONObject.parseObject(roomPriceInfo.toString());
                String roomCode = roomPriceJson.getString("RoomID");//子房型id
                JSONArray priceInfos = roomPriceJson.getJSONArray("PriceInfos");
                //PriceInfos
                for (Object priceInfo : priceInfos) {
                    JSONObject priceInfoJson = JSONObject.parseObject(priceInfo.toString());
                    String remainingRooms = priceInfoJson.getString("RemainingRooms");//可定房量
                    JSONArray dailyPrices = priceInfoJson.getJSONArray("DailyPrices");
                    //DailyPrices
                    for (Object dailyPrice : dailyPrices) {
                        PriceDetail priceDetail = new PriceDetail();
                        JSONArray prices = JSONObject.parseObject(dailyPrice.toString()).getJSONArray("Prices");
                        String useDay = JSONObject.parseObject(dailyPrice.toString()).getString("EffectiveDate");//每日价的生效日期
                        //Prices
                        for (Object price : prices) {
                            JSONObject priceJson = JSONObject.parseObject(price.toString());
                            String type = priceJson.getString("Type");//卖价 or 结算价?
                            String inclusiveAmount = priceJson.getString("InclusiveAmount");//税后价格
                            String currency = priceJson.getString("Currency");//货币种类
                            if ("DisplayCostCurrency".equals(type)) {
                                priceDetail.setDisplayCostCurrency(inclusiveAmount);
                            } else if ("OriginalCostCurrency".equals(type)) {
                                priceDetail.setOriginalCostCurrency(inclusiveAmount);
                            } else if ("DisplayCurrency".equals(type)) {
                                priceDetail.setDisplayCurrency(inclusiveAmount);
                            } else if ("OriginalCurrency".equals(type)) {
                                priceDetail.setOriginalCurrency(inclusiveAmount);
                            }
                            priceDetail.setCurrency(currency);
                            priceDetail.setUseDay(useDay);//请求的日期
                        }
                        priceDetail.setUpdateTimeStamp(System.currentTimeMillis() / 1000);
                        priceDetail.setMasterHotelNum(masterHotelNum);
                        priceDetail.setRoomId(roomId);
                        priceDetail.setRoomCode(roomCode);
                        priceDetail.setRemainingRooms(remainingRooms);
                        pdList.add(priceDetail);
                    }
                }
            }
        }
        return pdList;
    }

    //直连取消规则
    public static List<CancelDetail> getCancelDetailBean(String result, String masterHotelNum) {
        ArrayList<CancelDetail> cancelList = new ArrayList<>();
        JSONArray roomPriceItems = JSONObject.parseObject(result).getJSONArray("RoomPriceItems");
        //RoomPriceItems
        for (Object roomPriceItem : roomPriceItems) {
            JSONObject itemJson = JSONObject.parseObject(roomPriceItem.toString());
            String roomId = itemJson.getString("RoomTypeID");//物理房型id
            JSONArray roomPriceInfos = itemJson.getJSONArray("RoomPriceInfos");
            //RoomPriceInfos
            for (Object roomPriceInfo : roomPriceInfos) {
                JSONObject roomPriceJson = JSONObject.parseObject(roomPriceInfo.toString());
                String roomCode = roomPriceJson.getString("RoomID");//子房型id

                JSONArray cancelPolicyInfos = roomPriceJson.getJSONArray("CancelPolicyInfos");
                //CancelPolicyInfos
                for (Object cancelPolicyInfo : cancelPolicyInfos) {
                    JSONObject cancelJson = JSONObject.parseObject(cancelPolicyInfo.toString());
                    String start = cancelJson.getString("Start");//取消政策的生效时间。 备注：Start定义了最晚免费取消时间。Start时间之前，客人可免费取消；Start之后，客人需承担相应罚金。
                    String end = cancelJson.getString("End");//取消政策的失效时间
                    JSONArray penaltyAmount = cancelJson.getJSONArray("PenaltyAmount");
                    //PenaltyAmount
                    ArrayList<CancelEntity> ceList = new ArrayList<>();
                    for (Object penalty : penaltyAmount) {
                        CancelEntity cancelEntity = new CancelEntity();
                        JSONObject penaltyJson = JSONObject.parseObject(penalty.toString());
                        String type = penaltyJson.getString("Type");
                        String amount = penaltyJson.getString("Amount");
                        String currency = penaltyJson.getString("Currency");
                        cancelEntity.setType(type);
                        cancelEntity.setAmount(amount);
                        cancelEntity.setCurrency(currency);
                        ceList.add(cancelEntity);
                    }
                    CancelDetail cancelDetail = new CancelDetail();
                    cancelDetail.setUpdateTimeStamp(System.currentTimeMillis() / 1000);
                    cancelDetail.setMasterHotelNum(masterHotelNum);
                    cancelDetail.setRoomId(roomId);//物理房型id
                    cancelDetail.setRoomCode(roomCode);//子房型id
                    cancelDetail.setCancels(ceList);
                    cancelDetail.setStart(start);
                    cancelDetail.setEnd(end);
                    cancelList.add(cancelDetail);
                }
            }
        }
        return cancelList;
    }

}
