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
package com.taotao.cloud.payment.biz.jeepay.mch.ctrl.transfer;

import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.payment.biz.jeepay.core.constants.CS;
import com.taotao.cloud.payment.biz.jeepay.core.entity.MchApp;
import com.taotao.cloud.payment.biz.jeepay.core.entity.PayInterfaceConfig;
import com.taotao.cloud.payment.biz.jeepay.core.entity.PayInterfaceDefine;
import com.taotao.cloud.payment.biz.jeepay.core.exception.BizException;
import com.taotao.cloud.payment.biz.jeepay.core.model.ApiRes;
import com.taotao.cloud.payment.biz.jeepay.core.model.DBApplicationConfig;
import com.taotao.cloud.payment.biz.jeepay.core.utils.StringKit;
import com.taotao.cloud.payment.biz.jeepay.jeepay.JeepayClient;
import com.taotao.cloud.payment.biz.jeepay.jeepay.exception.JeepayException;
import com.taotao.cloud.payment.biz.jeepay.jeepay.model.TransferOrderCreateReqModel;
import com.taotao.cloud.payment.biz.jeepay.jeepay.request.TransferOrderCreateRequest;
import com.taotao.cloud.payment.biz.jeepay.jeepay.response.TransferOrderCreateResponse;
import com.taotao.cloud.payment.biz.jeepay.mch.ctrl.CommonCtrl;
import com.taotao.cloud.payment.biz.jeepay.service.impl.MchAppService;
import com.taotao.cloud.payment.biz.jeepay.service.impl.PayInterfaceConfigService;
import com.taotao.cloud.payment.biz.jeepay.service.impl.PayInterfaceDefineService;
import com.taotao.cloud.payment.biz.jeepay.service.impl.SysConfigService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 转账api
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/8/13 14:43
*/
@RestController
@RequestMapping("/api/mchTransfers")
public class MchTransferController extends CommonCtrl {

    @Autowired private MchAppService mchAppService;
    @Autowired private PayInterfaceConfigService payInterfaceConfigService;
    @Autowired private PayInterfaceDefineService payInterfaceDefineService;
    @Autowired private SysConfigService sysConfigService;

    /** 查询商户对应应用下支持的支付通道 **/
    @PreAuthorize("hasAuthority('ENT_MCH_TRANSFER_IF_CODE_LIST')")
    @GetMapping("/ifCodes/{appId}")
    public ApiRes ifCodeList(@PathVariable("appId") String appId) {


        List<String> ifCodeList = new ArrayList<>();
        payInterfaceConfigService.list(
                PayInterfaceConfig.gw().select(PayInterfaceConfig::getIfCode)
                        .eq(PayInterfaceConfig::getInfoType, CS.INFO_TYPE_MCH_APP)
                        .eq(PayInterfaceConfig::getInfoId, appId)
                        .eq(PayInterfaceConfig::getState, CS.PUB_USABLE)
        ).stream().forEach(r -> ifCodeList.add(r.getIfCode()));

        if(ifCodeList.isEmpty()){
            return ApiRes.ok(ifCodeList);
        }

        List<PayInterfaceDefine> result = payInterfaceDefineService.list(PayInterfaceDefine.gw().in(PayInterfaceDefine::getIfCode, ifCodeList));
        return ApiRes.ok(result);
    }



    /** 获取渠道侧用户ID **/
    @PreAuthorize("hasAuthority('ENT_MCH_TRANSFER_CHANNEL_USER')")
    @GetMapping("/channelUserId")
    public ApiRes channelUserId() {

        String appId = getValStringRequired("appId");
        MchApp mchApp = mchAppService.getById(appId);
        if(mchApp == null || mchApp.getState() != CS.PUB_USABLE || !mchApp.getMchNo().equals(getCurrentMchNo())){
            throw new BizException("商户应用不存在或不可用");
        }

        JSONObject param = getReqParamJSON();
        param.put("mchNo", getCurrentMchNo());
        param.put("appId", appId);
        param.put("ifCode", getValStringRequired("ifCode"));
        param.put("extParam", getValStringRequired("extParam"));
        param.put("reqTime", System.currentTimeMillis() + "");
        param.put("version", "1.0");
        param.put("signType", "MD5");

        DBApplicationConfig dbApplicationConfig = sysConfigService.getDBApplicationConfig();

        param.put("redirectUrl", dbApplicationConfig.getMchSiteUrl() + "/api/anon/channelUserIdCallback");

        param.put("sign", JeepayKit.getSign(param, mchApp.getAppSecret()));
        String url = StringKit.appendUrlQuery(dbApplicationConfig.getPaySiteUrl() + "/api/channelUserId/jump", param);

        return ApiRes.ok(url);
    }


    /** 调起下单接口 **/
    @PreAuthorize("hasAuthority('ENT_MCH_PAY_TEST_DO')")
    @PostMapping("/doTransfer")
    public ApiRes doTransfer() {
        handleParamAmount("amount");
        TransferOrderCreateReqModel model = getObject(TransferOrderCreateReqModel.class);

        MchApp mchApp = mchAppService.getById(model.getAppId());
        if(mchApp == null || mchApp.getState() != CS.PUB_USABLE || !mchApp.getMchNo().equals(getCurrentMchNo()) ){
            throw new BizException("商户应用不存在或不可用");
        }

        TransferOrderCreateRequest request = new TransferOrderCreateRequest();
        model.setMchNo(this.getCurrentMchNo());
        model.setAppId(mchApp.getAppId());
        model.setCurrency("CNY");
        request.setBizModel(model);

        JeepayClient jeepayClient = new JeepayClient(sysConfigService.getDBApplicationConfig().getPaySiteUrl(), mchApp.getAppSecret());

        try {
            TransferOrderCreateResponse response = jeepayClient.execute(request);
            if(response.getCode() != 0){
                throw new BizException(response.getMsg());
            }
            return ApiRes.ok(response.get());
        } catch (JeepayException e) {
            throw new BizException(e.getMessage());
        }
    }

}
