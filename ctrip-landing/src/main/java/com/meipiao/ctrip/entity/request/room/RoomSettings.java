package com.meipiao.ctrip.entity.request.room;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 8:53
 */
@Data
public class RoomSettings implements Serializable {

    /// <summary>
    /// 定义返回数据以何种语言显示。例如，cn表示中文，en表示英文，默认返回中文
    /// </summary>
    private String PrimaryLangID;

    private List<String> ExtendedNodes = new ArrayList<String>() {
        {
            //物理房型相关扩展节点
            add("RoomTypeInfo.Facilities");//房型设施设备
            add("RoomTypeInfo.Descriptions");//房型描述
                    add("RoomTypeInfo.ChildLimit");//儿童政策
                    add("RoomTypeInfo.RoomBedInfos");//床型信息
                    add("RoomTypeInfo.BnBHotel");    // 获取民宿房型专有信息
                    add("RoomTypeInfo.RoomTypeTags.ReservedData");//物理房型英文名称

                    //售卖房型相关扩展节点
                    add("RoomInfo.ApplicabilityInfo");//可适用人群
                    add("RoomInfo.RoomFGToPPInfo");//是否可以做现付转预付流程
                    add("RoomInfo.ChannelLimit");//售卖渠道限制
                    add("RoomInfo.RoomTags");//房型标签
                    add("RoomInfo.AreaApplicabilityInfo");//适用人群限制
                    add("RoomInfo.BookingRules");//预定限制
                    add("RoomInfo.Smoking");//吸烟信息（物理房型层废弃，售卖房型上可使用）
                    add("RoomInfo.IsNeedCustomerTelephone");//酒店是否需要客人联系电话

                    //物理房型相关扩展节点 (欲抛弃信息)
                    add("RoomTypeInfo.Pictures");//房型图片
                    add("RoomTypeInfo.BroadNet");//宽带信息;请使用物理房型层宽带信息，售卖房型层宽带信息暂停使用
                    add("RoomTypeInfo.Smoking");//已废弃，请使用售卖房型吸烟信息

                    //售卖房型相关扩展节点
                    add("RoomInfo.BroadNet");//已废弃，请使用物理房型层宽带信息，售卖房型层宽带信息暂停使用
                    add("RoomInfo.ExpressCheckout");//闪住信息
                    add("RoomInfo.RoomGiftInfos");//礼盒信息
                    add("RoomInfo.RoomPromotions");//房型促销信息
                    add("RoomInfo.HotelPromotions");//酒店促销信息
                    add("RoomInfo.MaskCampaignInfos");//该信息暂不对外开放
                    add("RoomInfo.RoomTags.HotelDiscount");//
        }
    };

    private List<SearchTagsItem> SearchTags ;
}
