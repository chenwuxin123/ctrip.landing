package com.meipiao.ctrip.entity.vo.imge;

import lombok.Data;

import java.io.Serializable;

/**
 * @author innoer
 * @version 1.0
 * @e-mail innoervince@163.com
 * @description ...
 * @date 2020/3/9 4:45 下午
 */
@Data
public class Image implements Serializable {

    private static final long serialVersionUID = 1821158776811629109L;

    /** 图片ID **/
    private Long imageId;
    /** 图片类型 1:房型 2:外景 3:大堂 4:设施 5: 其它 6:宴会厅 7:会议厅 8:门票 9:其它服务 **/
    private Integer imageType;
    /** 是否主图 1:主图 0:非主图 **/
    private Integer isMain;
    /** 图片地址(图片的httpurl或ftp路径) **/
    private String url;

    //---------------------新增字段--------------------//
    /** 图片标题(eg:外观) **/
    private String caption;

}
