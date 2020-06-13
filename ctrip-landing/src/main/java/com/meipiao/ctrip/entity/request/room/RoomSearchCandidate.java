package com.meipiao.ctrip.entity.request.room;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 8:52
 */
@Data
public class RoomSearchCandidate implements Serializable {
    private String HotelID;
    private List<String> RoomIDs;
}
