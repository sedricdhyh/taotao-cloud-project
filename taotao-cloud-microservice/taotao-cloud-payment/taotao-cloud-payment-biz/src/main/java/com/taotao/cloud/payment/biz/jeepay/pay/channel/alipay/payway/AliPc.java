/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.payment.biz.jeepay.pay.channel.alipay.payway;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.taotao.cloud.payment.biz.jeepay.core.constants.CS;
import com.taotao.cloud.payment.biz.jeepay.core.entity.PayOrder;
import com.taotao.cloud.payment.biz.jeepay.core.utils.AmountUtil;
import com.taotao.cloud.payment.biz.jeepay.pay.channel.alipay.AlipayKit;
import com.taotao.cloud.payment.biz.jeepay.pay.channel.alipay.AlipayPaymentService;
import com.taotao.cloud.payment.biz.jeepay.pay.exception.ChannelException;
import com.taotao.cloud.payment.biz.jeepay.pay.model.MchAppConfigContext;
import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.AbstractRS;
import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.payorder.payway.AliPcOrderRQ;
import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.payorder.payway.AliPcOrderRS;
import com.taotao.cloud.payment.biz.jeepay.pay.util.ApiResBuilder;
import org.springframework.stereotype.Service;

/*
* 支付宝 PC支付
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:21
*/
@Service("alipayPaymentByAliPcService") //Service Name需保持全局唯一性
public class AliPc extends AlipayPaymentService {

    @Override
    public String preCheck(UnifiedOrderRQ rq, PayOrder payOrder) {
        return null;
    }

    @Override
    public AbstractRS pay(UnifiedOrderRQ rq, PayOrder payOrder, MchAppConfigContext mchAppConfigContext){

        AliPcOrderRQ bizRQ = (AliPcOrderRQ) rq;

        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(payOrder.getPayOrderId());
        model.setSubject(payOrder.getSubject()); //订单标题
        model.setBody(payOrder.getBody()); //订单描述信息
        model.setTotalAmount(AmountUtil.convertCent2Dollar(payOrder.getAmount().toString()));  //支付金额
        model.setTimeExpire(DateUtil.format(payOrder.getExpiredTime(), DatePattern.NORM_DATETIME_FORMAT));  // 订单超时时间
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setQrPayMode("2"); //订单码-跳转模式
        req.setNotifyUrl(getNotifyUrl()); // 设置异步通知地址
        req.setReturnUrl(getReturnUrl()); // 同步跳转地址
        req.setBizModel(model);

        //统一放置 isv接口必传信息
        AlipayKit.putApiIsvInfo(mchAppConfigContext, req, model);

        // 构造函数响应数据
        AliPcOrderRS res = ApiResBuilder.buildSuccess(AliPcOrderRS.class);

        try {
            if(CS.PAY_DATA_TYPE.FORM.equals(bizRQ.getPayDataType())){
                res.setFormContent(configContextQueryService.getAlipayClientWrapper(mchAppConfigContext).getAlipayClient().pageExecute(req).getBody());
            }else{
                res.setPayUrl(configContextQueryService.getAlipayClientWrapper(mchAppConfigContext).getAlipayClient().pageExecute(req, "GET").getBody());
            }
        }catch (AlipayApiException e) {
            throw ChannelException.sysError(e.getMessage());
        }

        ChannelRetMsg channelRetMsg = new ChannelRetMsg();
        res.setChannelRetMsg(channelRetMsg);

        //放置 响应数据
        channelRetMsg.setChannelState(ChannelRetMsg.ChannelState.WAITING);
        return res;
    }

}
