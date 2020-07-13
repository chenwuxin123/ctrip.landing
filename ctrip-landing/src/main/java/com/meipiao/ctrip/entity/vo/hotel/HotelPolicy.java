package com.meipiao.ctrip.entity.vo.hotel;

import java.io.Serializable;
import java.util.List;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/4 5:15 下午
 */
public class HotelPolicy implements Serializable {

    private static final long serialVersionUID = 5119866846243754865L;

    /** 酒店政策类别名称 **/
    private String policyName;

    /** 酒店政策类别名称 **/
    private List<String> policyItems;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public List<String> getPolicyItems() {
        return policyItems;
    }

    public void setPolicyItems(List<String> policyItems) {
        this.policyItems = policyItems;
    }
}
