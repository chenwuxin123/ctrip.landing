package com.meipiao.ctrip.entity.request.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Chenwx
 * @Date: 2020/6/12 17:39
 */
@Data
public class HSearchCandidate implements Serializable {
    private SearchByCityID SearchByCityID;
}
