package com.meipiao.ctrip.entity.vo.hotel;

import java.io.Serializable;
import java.util.List;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/4 5:19 下午
 */
public class HotelAround implements Serializable {

    private static final long serialVersionUID = 737931283816622450L;

    /** 酒店周边类别名称 **/
    private String aroundName;

    /** 酒店周边列表 **/
    private List<String> AroundItems;

    public String getAroundName() {
        return aroundName;
    }

    public void setAroundName(String aroundName) {
        this.aroundName = aroundName;
    }

    public List<String> getAroundItems() {
        return AroundItems;
    }

    public void setAroundItems(List<String> aroundItems) {
        AroundItems = aroundItems;
    }
}
