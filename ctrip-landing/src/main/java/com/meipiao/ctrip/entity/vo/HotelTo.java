package com.meipiao.ctrip.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/9 3:02 下午
 */
@Data
public class HotelTo implements Serializable {

    private static final long serialVersionUID = 6033899381176487195L;

    /** 总条数 **/
    private Integer totalCount;
    /** 总页数 **/
    private Integer totalPage;
    /** 当前页数 **/
    private Integer page;
    /** 酒店列表 **/
    private List<Hotel> hotels;
    /** 高价限制 **/
    private Integer highLimit;
    /** 是否拆标0否1是 **/
    private Short isDismantling;
    /** 预订酒店 **/
    private List<Map<String,Object>> pmap;


}
