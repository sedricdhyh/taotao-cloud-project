package com.taotao.cloud.payment.biz.bootx.dto.payconfig;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 支付通道
* @author xxm  
* @date 2021/6/30 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PayChannelDto extends BaseDto implements Serializable {

    /**
     * 通道代码(唯一)
     * @see PayChannelEnum
     */
    private String code;

    /** 名称 */
    private String name;

    /** 页面展示：卡片-图标 */
    private String icon;

    /** 页面展示：卡片-背景色 */
    private String bgColor;

    /** 状态 */
    private Integer state;

    /** 备注 */
    private String remark;
}
