package com.meipiao.ctrip.entity.vo.hotel;

import java.io.Serializable;
import java.util.List;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/4 5:11 下午
 */
public class HotelFacility implements Serializable {

    private static final long serialVersionUID = -4585619981455931372L;

    /** 酒店设施类别名称 **/
    private String facilityName;

    /** 酒店设施列表 **/
    private List<String> facilityItems;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public List<String> getFacilityItems() {
        return facilityItems;
    }

    public void setFacilityItems(List<String> facilityItems) {
        this.facilityItems = facilityItems;
    }
}
