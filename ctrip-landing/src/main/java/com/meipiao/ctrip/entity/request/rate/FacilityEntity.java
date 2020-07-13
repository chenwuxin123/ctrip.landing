package com.meipiao.ctrip.entity.request.rate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenwuxin
 * @version 1.0.0
 * @description
 * @date 2020/7/8 13:03
 */
public class FacilityEntity implements Serializable {
    /**
     * 界定返回的房型必须满足的儿童年龄和儿童人数限制。
     * 数组的长度表示房型需满足儿童入住人数要求，数组的每一位的值表示房型需满足儿童入住年龄限制。
     */
    private List<String> ChildrenAgeList = new ArrayList<>();

    private PriceRange priceRange;

    public List<String> getChildrenAgeList() {
        return ChildrenAgeList;
    }

    public void setChildrenAgeList(List<String> childrenAgeList) {
        ChildrenAgeList = childrenAgeList;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }
}
