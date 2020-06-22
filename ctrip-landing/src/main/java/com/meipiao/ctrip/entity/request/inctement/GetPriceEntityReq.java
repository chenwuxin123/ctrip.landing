package com.meipiao.ctrip.entity.request.inctement;

import com.meipiao.ctrip.entity.request.city.SearchCandidate;
import com.meipiao.ctrip.entity.request.page.PagingSettings;
import lombok.Data;

import java.io.Serializable;

/**
 * 价格增量请求实体
 * @Author: Chenwx
 * @Date: 2020/6/22 10:30
 */
@Data
public class GetPriceEntityReq implements Serializable {
    private IncrPriceSearchCandidate SearchCandidate;
    private IncrPriceSettings Settings;
    private com.meipiao.ctrip.entity.request.page.PagingSettings PagingSettings;
}
