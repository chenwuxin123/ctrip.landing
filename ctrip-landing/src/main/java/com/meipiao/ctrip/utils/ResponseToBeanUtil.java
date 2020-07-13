package com.meipiao.ctrip.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meipiao.ctrip.entity.response.city.Coordinates;
import com.meipiao.ctrip.entity.response.city.Destination;
import com.meipiao.ctrip.entity.response.hotel.HotelDetail;
import com.meipiao.ctrip.entity.response.hotel.HotelIdDetail;
import com.meipiao.ctrip.entity.response.hotel.TransportationInfos;
import com.meipiao.ctrip.entity.response.rate.DailyPrices;
import com.meipiao.ctrip.entity.response.rate.RoomPriceRes;
import com.meipiao.ctrip.entity.response.room.*;
import com.meipiao.ctrip.entity.vo.hotel.HotelFacility;
import com.meipiao.ctrip.entity.vo.hotel.HotelPolicy;
import com.meipiao.ctrip.entity.vo.imge.Image;

import java.util.*;

/**
 * 将相应结果转化成相应的Bean
 *
 * @Author: Chenwx
 * @Date: 2020/6/10 10:54
 */
public class ResponseToBeanUtil {
    public static void main(String[] args) {
        String res = "{\"ResponseStatus\":{\"Timestamp\":\"2020-07-09T15:18:04.779+08:00\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[]},\"PagingInfo\":{\"LastRecordID\":\"\"},\"RoomPriceItems\":[{\"RoomPriceInfos\":[{\"CancelPolicyInfos\":[{\"PenaltyAmount\":[],\"Start\":\"0001-01-01T00:00:00.0000000\",\"End\":\"2020-07-11T00:00:00.0000000+08:00\"}],\"ReserveTimeLimitInfo\":{\"LatestReserveTime\":\"9999-12-31T23:59:59.0000999+08:00\"},\"HoldDeadline\":{},\"PriceInfos\":[{\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":102.6120,\"InclusiveAmount\":102.6120,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":102.6120,\"InclusiveAmount\":102.6120,\"Currency\":\"CNY\"}],\"DailyPrices\":[{\"MealInfo\":{\"NumberOfBreakfast\":0,\"NumberOfLunch\":0,\"NumberOfDinner\":0,\"IsOptionalMeal\":false},\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":51.3060,\"InclusiveAmount\":51.3060,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":51.3060,\"InclusiveAmount\":51.3060,\"Currency\":\"CNY\"}],\"Cashbacks\":[],\"EffectiveDate\":\"2020-07-09T00:00:00.0000000+08:00\",\"GuaranteeCode\":\"2\"},{\"MealInfo\":{\"NumberOfBreakfast\":0,\"NumberOfLunch\":0,\"NumberOfDinner\":0,\"IsOptionalMeal\":false},\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":51.3060,\"InclusiveAmount\":51.3060,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":51.3060,\"InclusiveAmount\":51.3060,\"Currency\":\"CNY\"}],\"Cashbacks\":[],\"EffectiveDate\":\"2020-07-10T00:00:00.0000000+08:00\",\"GuaranteeCode\":\"2\"}],\"Taxes\":[],\"Fees\":[],\"PayType\":\"PP\",\"RatePlanCategory\":\"501\",\"IsCanReserve\":true,\"IsGuarantee\":false,\"IsInstantConfirm\":true,\"RemainingRooms\":\"2\",\"IsPromotion\":\"T\",\"IsFastConfirm\":true}],\"RoomID\":166609473,\"RoomName\":\"温馨6人间(仅女生入住)\"}],\"RoomTypeID\":74005651},{\"RoomPriceInfos\":[{\"CancelPolicyInfos\":[{\"PenaltyAmount\":[],\"Start\":\"0001-01-01T00:00:00.0000000\",\"End\":\"2020-07-11T00:00:00.0000000+08:00\"}],\"ReserveTimeLimitInfo\":{\"LatestReserveTime\":\"9999-12-31T23:59:59.0000999+08:00\"},\"HoldDeadline\":{},\"PriceInfos\":[{\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":113.6280,\"InclusiveAmount\":113.6280,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":113.6280,\"InclusiveAmount\":113.6280,\"Currency\":\"CNY\"}],\"DailyPrices\":[{\"MealInfo\":{\"NumberOfBreakfast\":0,\"NumberOfLunch\":0,\"NumberOfDinner\":0,\"IsOptionalMeal\":false},\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":56.8140,\"InclusiveAmount\":56.8140,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":56.8140,\"InclusiveAmount\":56.8140,\"Currency\":\"CNY\"}],\"Cashbacks\":[],\"EffectiveDate\":\"2020-07-09T00:00:00.0000000+08:00\",\"GuaranteeCode\":\"2\"},{\"MealInfo\":{\"NumberOfBreakfast\":0,\"NumberOfLunch\":0,\"NumberOfDinner\":0,\"IsOptionalMeal\":false},\"Prices\":[{\"Type\":\"DisplayCostCurrency\",\"ExclusiveAmount\":56.8140,\"InclusiveAmount\":56.8140,\"Currency\":\"CNY\"},{\"Type\":\"OriginalCostCurrency\",\"ExclusiveAmount\":56.8140,\"InclusiveAmount\":56.8140,\"Currency\":\"CNY\"}],\"Cashbacks\":[],\"EffectiveDate\":\"2020-07-10T00:00:00.0000000+08:00\",\"GuaranteeCode\":\"2\"}],\"Taxes\":[],\"Fees\":[],\"PayType\":\"PP\",\"RatePlanCategory\":\"501\",\"IsCanReserve\":true,\"IsGuarantee\":false,\"IsInstantConfirm\":false,\"RemainingRooms\":\"10+\",\"IsPromotion\":\"T\",\"IsFastConfirm\":false}],\"RoomID\":166608980,\"RoomName\":\"简洁4人间(仅女生入住)\"}],\"RoomTypeID\":74005717}],\"LogInfo\":{\"LogID\":\"dd07ff0b-79f0-4b12-acb4-b477bf5d03aa\"}}\n";
        List<RoomPriceRes> price = getPrice(res, "213214");
        for (RoomPriceRes roomPriceRes : price) {
            String s = JSONObject.toJSONString(roomPriceRes);
            System.out.println(s);
        }
    }

    public static String getResponseStatus(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取状态码
        JSONObject responseStatus = JSONObject.parseObject(obj.getString("ResponseStatus"));
        if (responseStatus == null) {
            return "Fail";
        }
        return responseStatus.getString("Ack");
    }

    public static String getResponseTimestamp(String result) {
        JSONObject obj = JSONObject.parseObject(result);
        //获取时间戳
        JSONObject responseStatus = JSONObject.parseObject(obj.getString("ResponseStatus"));
        return responseStatus.getString("Timestamp");
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
    public static ArrayList<HotelIdDetail> getHotelIdDetailBean(String result, String cityJson) {
        JSONObject cityObj = JSONObject.parseObject(cityJson);
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
        Long masterHotelNum = Long.parseLong(hotelStaticInfo.getString("HotelID"));
        String hotelName = hotelStaticInfo.getString("HotelName");
        JSONArray hotelTags = hotelStaticInfo.getJSONArray("HotelTags");
        //酒店和地址英文名称
        if (hotelTags != null) {
            for (Object hotelTag : hotelTags) {
                JSONObject hotelTagsJson = JSONObject.parseObject(hotelTag.toString());
                if ("HotelNameEN".equals(hotelTagsJson.getString("Name"))) {
                    //new 对象 到时候直接set
                    hotelNameEN = hotelTagsJson.getString("Value");
                } else if ("HotelAddressEN".equals(hotelTagsJson.getString("Name"))) {
                    hotelAddressEN = hotelTagsJson.getString("Value");
                }
            }
        }

        String hotelApplicability = hotelStaticInfo.getJSONObject("ApplicabilityInfo").getString("HotelApplicability");//酒店是否有(3,仅接待大陆客人;4,仅接待大陆和港澳台客人;5,接待大陆、港澳台及外国客人)
        Integer starRating = hotelStaticInfo.getInteger("StarRating"); //星级
        Boolean isOfficialRating = hotelStaticInfo.getBoolean("IsOfficialRating");//标明星级是否有政府机构评定
        String openYear = hotelStaticInfo.getString("OpenYear");
        openYear = openYear + " 00:00:00";
        String roomQuantity = hotelStaticInfo.getString("RoomQuantity");//酒店的客房数量
        String telephone = hotelStaticInfo.getJSONObject("ContactInfo").getString("Telephone");//电话
        String fax = hotelStaticInfo.getJSONObject("ContactInfo").getString("Fax");//传真
        //GeoInfo
        JSONObject geoInfo = hotelStaticInfo.getJSONObject("GeoInfo");
        String address = geoInfo.getString("Address");//地址
        String postalCode = geoInfo.getString("PostalCode");//酒店所在的城市区域代码
        String country = geoInfo.getJSONObject("Country").getString("Name");//国家
        String province = geoInfo.getJSONObject("Province").getString("Name");//省份
        String city = geoInfo.getJSONObject("City").getString("Name");//城市

        //BusinessDistrict
        String businessName = "";
        JSONArray businessDistricts = geoInfo.getJSONArray("BusinessDistrict");
        if (businessDistricts != null) {
            for (Object businessDistrict : businessDistricts) {
                businessName = JSONObject.parseObject(businessDistrict.toString()).getString("Name");
            }
        }

        //Coordinates
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

        //Pictures
        List<Image> hotelPictures = new ArrayList<>();
        JSONArray pictures = hotelStaticInfo.getJSONArray("Pictures");
        for (Object picture : pictures) {
            JSONObject pictureJson = JSONObject.parseObject(picture.toString());
            if (pictureJson != null) {
                String pictureUrl = pictureJson.getString("URL");
                String caption = pictureJson.getString("Caption");//图片标题
                Image image = new Image();
                image.setCaption(caption);
                image.setUrl(pictureUrl);
                hotelPictures.add(image);
            }
        }

        //Descriptions
        JSONArray descriptions = hotelStaticInfo.getJSONArray("Descriptions");
        String text = "";
        if (descriptions.size() > 1) {
            text = JSONObject.parseObject(descriptions.get(1).toString()).getString("Text");
        }

        //FacilitiesV2(酒店设施)
        JSONArray facilitiesV2 = hotelStaticInfo.getJSONArray("FacilitiesV2");
        ArrayList<String> hotelFacilities = new ArrayList<>();
        if (facilitiesV2 != null) {
            for (Object facilities : facilitiesV2) {
                String name = JSONObject.parseObject(facilities.toString()).getString("Name");
                hotelFacilities.add(name);
            }
        }
        HotelFacility hotelFacility = new HotelFacility();
        hotelFacility.setFacilityItems(hotelFacilities);
        ArrayList<HotelFacility> fcs = new ArrayList<>();
        fcs.add(hotelFacility);

        //Policies
        JSONArray policies = hotelStaticInfo.getJSONArray("Policies");
        ArrayList<String> hotelPolicie = new ArrayList<>();
        if (policies != null) {
            for (Object policy : policies) {
                String pText = JSONObject.parseObject(policy.toString()).getString("Text");
                hotelPolicie.add(pText);
            }
        }

        HotelPolicy hotelPolicy = new HotelPolicy();
        hotelPolicy.setPolicyItems(hotelPolicie);
        ArrayList<HotelPolicy> pls = new ArrayList<>();
        pls.add(hotelPolicy);

        //TransportationInfos(酒店周边交通信息)
        JSONArray transportationInfos = hotelStaticInfo.getJSONArray("TransportationInfos");
        ArrayList<TransportationInfos> tsiList = new ArrayList<>();
        if (transportationInfos != null) {
            for (Object transportationInfo : transportationInfos) {
                TransportationInfos tsi = new TransportationInfos();
                String transportName = JSONObject.parseObject(transportationInfo.toString()).getString("Name");
                Double distance = JSONObject.parseObject(transportationInfo.toString()).getDouble("Distance");
                String directions = JSONObject.parseObject(transportationInfo.toString()).getString("Directions");
                Double timeTaken = JSONObject.parseObject(transportationInfo.toString()).getDouble("TimeTaken");
                tsi.setName(transportName);
                tsi.setDistance(distance);
                tsi.setDirections(directions);
                tsi.setTimeTaken(timeTaken);
                tsiList.add(tsi);
            }
        }

        HotelDetail hotelDetailBean = new HotelDetail();
        //set
        hotelDetailBean.setTransportationInfos(tsiList);
        hotelDetailBean.setHotelPolicys(pls);
        hotelDetailBean.setHotelFacilitys(fcs);
        hotelDetailBean.setHotelIntroduce(text);
        hotelDetailBean.setBusinessName(businessName);
        hotelDetailBean.setPictures(hotelPictures);
        hotelDetailBean.setHotelId(masterHotelNum);
        hotelDetailBean.setDataFlag("3".equals(hotelApplicability) || "5".equals(hotelApplicability) ? 1 : 0);
        hotelDetailBean.setHotelName(hotelName);
        hotelDetailBean.setHotelEngName(hotelNameEN);
        hotelDetailBean.setAddressEn(hotelAddressEN);
        hotelDetailBean.setHotelStar(starRating);
        hotelDetailBean.setIsOfficialRating(isOfficialRating ? 1 : 0);
        hotelDetailBean.setPraciceDate(openYear);
        hotelDetailBean.setRoomNum(roomQuantity);
        hotelDetailBean.setAddress(address);
        hotelDetailBean.setCoordinates(coordinatesWays);
        hotelDetailBean.setCountryName(country);
        hotelDetailBean.setProvinceName(province);
        hotelDetailBean.setCityName(city);
        hotelDetailBean.setPostCode(postalCode);
        hotelDetailBean.setTelephone(telephone);
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
                    bedInfoBean.setId(bedInfoId);
                    bedInfoBean.setName(bedInfoName);
                    bedInfoBean.setNumberOfBeds(numberOfBeds);
                    bedInfoBean.setBedWidth(bedWidth);
                    bedInfosList.add(bedInfoBean);
                }
                Beds beds = new Beds();
                beds.setId(badId);
                beds.setName(bedName);
                beds.setBedInfo(bedInfosList);
                bedList.add(beds);
            }

            //Pictures
            JSONArray pictures = jsonRoomType.getJSONArray("Pictures");
            List<String> roomPicture = new ArrayList<>();
            for (Object picture : pictures) {
                String pictureURL = JSON.parseObject(picture.toString()).getString("URL");
                roomPicture.add(pictureURL);
            }

            //子房型
            List<SubRoomDetail> subList = new ArrayList<>();
            //解析RoomInfos
            JSONArray roomInfos = JSONObject.parseObject(roomStaticInfo.toString()).getJSONArray("RoomInfos");
            for (Object roomInfo : roomInfos) {
                JSONObject subRoomJson = JSONObject.parseObject(roomInfo.toString());
                String subRoomId = subRoomJson.getString("RoomID");//子房型id
                String roomName = subRoomJson.getString("RoomName");
                String payType = subRoomJson.getString("PayType");
                Integer subRoomQuantity = subRoomJson.getInteger("RoomQuantity");
                Integer subMaxOccupancy = subRoomJson.getInteger("MaxOccupancy");
                String subAreaRange = subRoomJson.getString("AreaRange");
                String subFloorRange = subRoomJson.getString("FloorRange");
                Integer subHasWindow = subRoomJson.getInteger("HasWindow");
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
                subRoomDetail.setHotelId(Long.parseLong(masterHotelNum));
                subRoomDetail.setRoomCode(Long.parseLong(subRoomId));
                subRoomDetail.setRoomId(Long.parseLong(roomId));
                subRoomDetail.setRoomName(roomName);
                subRoomDetail.setPayType(payType);
                subRoomDetail.setRoomQuantity(subRoomQuantity);
                subRoomDetail.setMaxOccupancy(subMaxOccupancy);
                subRoomDetail.setAreaRange(subAreaRange);
                subRoomDetail.setFloorRange(subFloorRange);
                subRoomDetail.setHasWindow(subHasWindow);
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

            RoomDetail roomDetail = new RoomDetail();
            roomDetail.setSubRoom(subList);
            roomDetail.setPictures(roomPicture);
            roomDetail.setUpdateTimeStamp(System.currentTimeMillis());
            roomDetail.setHotelId(Long.parseLong(masterHotelNum));
            roomDetail.setRoomId(Long.parseLong(roomId));
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
                subRoomDetail.setHotelId(Long.parseLong(masterHotelNum));
                subRoomDetail.setRoomCode(Long.parseLong(subRoomId));
                subRoomDetail.setRoomId(Long.parseLong(roomId));
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

    public static List<RoomPriceRes> getPrice(String result, String masterHotelNum) {
        ArrayList<RoomPriceRes> roomPriceList = new ArrayList<>();

        JSONArray roomPriceItems = JSONObject.parseObject(result).getJSONArray("RoomPriceItems");
        if (roomPriceItems == null) {
            return roomPriceList;
        }

        //RoomPriceItems
        for (Object roomPriceItem : roomPriceItems) {
            JSONObject itemJson = JSONObject.parseObject(roomPriceItem.toString());
            String roomId = itemJson.getString("RoomTypeID");//物理房型id
            JSONArray roomPriceInfos = itemJson.getJSONArray("RoomPriceInfos");

            //RoomPriceInfos
            for (Object roomPriceInfo : roomPriceInfos) {
                RoomPriceRes roomPriceRes = new RoomPriceRes();
                ArrayList<DailyPrices> dpList = new ArrayList<>();

                JSONObject roomPriceJson = JSONObject.parseObject(roomPriceInfo.toString());
                String roomCode = roomPriceJson.getString("RoomID");//子房型id
                String roomName = roomPriceJson.getString("RoomName");//子房型名称

                String latestReserveTime = roomPriceJson.getJSONObject("ReserveTimeLimitInfo").getString("LatestReserveTime");//最后预定时间
                String start = "";//取消政策的生效时间。 备注：Start定义了最晚免费取消时间。Start时间之前，客人可免费取消；Start之后，客人需承担相应罚金。
                String end = "";//取消政策的失效时间
                Double totalPirce = null;
                String remainingRooms = "";//可定房量
                String payType = "";
                Boolean isCanReserve = null;
                Boolean isGuarantee = null;
                Boolean isPromotion = null;


                //CancelPolicyInfos
                JSONArray cancelPolicyInfos = roomPriceJson.getJSONArray("CancelPolicyInfos");
                for (Object cancelPolicyInfo : cancelPolicyInfos) {
                    JSONObject cancelJson = JSONObject.parseObject(cancelPolicyInfo.toString());
                    start = cancelJson.getString("Start");
                    end = cancelJson.getString("End");
                }

                JSONArray priceInfos = roomPriceJson.getJSONArray("PriceInfos");
                //PriceInfos
                for (Object priceInfo : priceInfos) {
                    JSONObject priceInfoJson = JSONObject.parseObject(priceInfo.toString());
                    remainingRooms = priceInfoJson.getString("RemainingRooms");
                    payType = priceInfoJson.getString("PayType");//支付类型，PP-预付，FG-到付
                    isCanReserve = priceInfoJson.getBoolean("IsCanReserve");//售卖房型是否可预订，true-可预订，false-不可预订
                    isGuarantee = priceInfoJson.getBoolean("IsGuarantee");//售卖房型是否需担保，true-需担保，false-不需担保
                    isPromotion = priceInfoJson.getBoolean("IsPromotion");//是否已包含促销

                    //Prices(最外层总价格)
                    JSONArray totalPirces = priceInfoJson.getJSONArray("Prices");
                    for (Object price : totalPirces) {
                        Integer inclusiveAmount = JSONObject.parseObject(price.toString()).getInteger("InclusiveAmount");
                        totalPirce = Double.valueOf(inclusiveAmount);
                    }

                    //DailyPrices
                    JSONArray dailyPrices = priceInfoJson.getJSONArray("DailyPrices");
                    for (Object dailyPrice : dailyPrices) {
                        DailyPrices dailyPricesBean = new DailyPrices();//内层bean

                        String cffectiveDate = JSONObject.parseObject(dailyPrice.toString()).getString("EffectiveDate");//每日价的生效日期
                        String useDay = cffectiveDate.replace("T00:00:00.0000000+08:00", " 00:00:00");

                        //MealInfo
                        String mealInfo = JSONObject.parseObject(dailyPrice.toString()).getString("MealInfo");
                        Integer breakfast = JSONObject.parseObject(mealInfo).getInteger("NumberOfBreakfast");
                        Integer lunch = JSONObject.parseObject(mealInfo).getInteger("NumberOfLunch");
                        Integer dinner = JSONObject.parseObject(mealInfo).getInteger("NumberOfDinner");

                        Double inclusiveAmount = null;
                        String currency = "";
                        JSONArray prices = JSONObject.parseObject(dailyPrice.toString()).getJSONArray("Prices");
                        //Prices
                        for (Object price : prices) {
                            JSONObject priceJson = JSONObject.parseObject(price.toString());
                            inclusiveAmount = Double.valueOf(priceJson.getInteger("InclusiveAmount"));
                            currency = priceJson.getString("Currency");
                        }

                        dailyPricesBean.setBreakfast(breakfast);//早
                        dailyPricesBean.setLunch(lunch);//中
                        dailyPricesBean.setDinner(dinner);//晚
                        dailyPricesBean.setCurrency(currency);//货币种类
                        dailyPricesBean.setEffectiveDate(useDay);//生效日期
                        dailyPricesBean.setDisplayCurrency(inclusiveAmount);//税后价格
                        dpList.add(dailyPricesBean);

                    }
                }
                roomPriceRes.setHotelId(Long.parseLong(masterHotelNum));
                roomPriceRes.setRoomId(Long.parseLong(roomId));
                roomPriceRes.setRoomCode(Long.parseLong(roomCode));
                roomPriceRes.setRoomName(roomName);
                roomPriceRes.setStart(start);
                roomPriceRes.setEnd(end);
                roomPriceRes.setLatestReserveTime(latestReserveTime);
                roomPriceRes.setTotalPrice(totalPirce);
                roomPriceRes.setRemainingRooms(remainingRooms);
                roomPriceRes.setPayType(payType);
                roomPriceRes.setCanReserve(isCanReserve);
                roomPriceRes.setGuarantee(isGuarantee);
                roomPriceRes.setPromotion(isPromotion);
                roomPriceRes.setDailyPrices(dpList);
                roomPriceList.add(roomPriceRes);
            }
        }
        return roomPriceList;
    }


    //价格增量
    public static List<String> getIncrementPriceBean(String result) {
        ArrayList<String> list = new ArrayList<>();
        JSONArray changeInfos = JSONObject.parseObject(result).getJSONArray("ChangeInfos");
        if (changeInfos == null) {
            return list;
        }
        for (Object changeInfo : changeInfos) {
            JSONObject changeBean = JSONObject.parseObject(changeInfo.toString());
            String hotelId = changeBean.getString("HotelID");
            list.add(hotelId);
        }
        return list;
    }

    //查询
    public static Map<Long, Long> getLowestPrice(String result) {
        HashMap<Long, Long> priceMap = new HashMap<>();
        JSONArray hotelDataLists = JSONObject.parseObject(result).getJSONArray("HotelDataLists");
        if (hotelDataLists == null) {
            return priceMap;
        }
        for (Object hotelDataList : hotelDataLists) {
            String hotelStatusEntity = JSONObject.parseObject(hotelDataList.toString()).getString("HotelStatusEntity");
            JSONObject statusBean = JSONObject.parseObject(hotelStatusEntity);
            if (statusBean == null) {
                return priceMap;
            }
            Long hotelId = statusBean.getLong("Hotel");
            Long minPrice = statusBean.getLong("MinPrice");
            priceMap.put(hotelId, minPrice);
        }
        return priceMap;
    }


}
