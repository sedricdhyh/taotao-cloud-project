package com.taotao.cloud.payment.biz.kit.plugin.wechat.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一下单-订单金额
 */
@Data
@Accessors(chain = true)
public class Amount {

    /**
     * 总金额
     */
    private Integer total;

    /**
     * 货币类型
     */
    private String currency = "CNY";

    /**
     * 退款金额
     */
    private Integer refund;
}
