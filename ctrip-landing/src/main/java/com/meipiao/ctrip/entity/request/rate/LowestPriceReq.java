package com.meipiao.ctrip.entity.request.rate;


import java.io.Serializable;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description 查询酒店最低价房型请求体
 * @date 2020/7/8 12:39
 */
public class LowestPriceReq implements Serializable {
    private SearchTypeEntity searchTypeEntity;
    private PublicSearchParameter publicSearchParameter;
    private FacilityEntity facilityEntity;
    private OrderBy orderBy;

    public SearchTypeEntity getSearchTypeEntity() {
        return searchTypeEntity;
    }

    public void setSearchTypeEntity(SearchTypeEntity searchTypeEntity) {
        this.searchTypeEntity = searchTypeEntity;
    }

    public PublicSearchParameter getPublicSearchParameter() {
        return publicSearchParameter;
    }

    public void setPublicSearchParameter(PublicSearchParameter publicSearchParameter) {
        this.publicSearchParameter = publicSearchParameter;
    }

    public FacilityEntity getFacilityEntity() {
        return facilityEntity;
    }

    public void setFacilityEntity(FacilityEntity facilityEntity) {
        this.facilityEntity = facilityEntity;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }
}
