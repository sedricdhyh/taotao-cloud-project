package com.taotao.cloud.payment.biz.bootx.core.pay.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**   
* 支付回调处理结果
* @author xxm
* @date 2021/6/22 
*/
@Data
@Accessors(chain = true)
public class PayCallbackResult {

    /**
     * 处理状态
     * @see PayStatusCode#NOTIFY_PROCESS_SUCCESS
     */
    private int code;

    /** 提示信息 */
    private String msg;
}
