package com.taotao.cloud.payment.biz.bootx.core.paymodel.alipay.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ijpay.alipay.AliPayApi;
import com.taotao.cloud.payment.biz.bootx.code.pay.PayStatusCode;
import com.taotao.cloud.payment.biz.bootx.code.pay.PayWayCode;
import com.taotao.cloud.payment.biz.bootx.code.pay.PayWayEnum;
import com.taotao.cloud.payment.biz.bootx.code.paymodel.AliPayCode;
import com.taotao.cloud.payment.biz.bootx.code.paymodel.AliPayWay;
import com.taotao.cloud.payment.biz.bootx.core.pay.local.AsyncPayInfoLocal;
import com.taotao.cloud.payment.biz.bootx.core.payment.entity.Payment;
import com.taotao.cloud.payment.biz.bootx.core.paymodel.alipay.entity.AlipayConfig;
import com.taotao.cloud.payment.biz.bootx.dto.pay.AsyncPayInfo;
import com.taotao.cloud.payment.biz.bootx.exception.payment.PayFailureException;
import com.taotao.cloud.payment.biz.bootx.param.pay.PayModeParam;
import com.taotao.cloud.payment.biz.bootx.param.paymodel.alipay.AliPayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 支付宝支付service
 * @author xxm
 * @date 2021/2/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AliPayService {

    /**
     * 支付前检查支付方式是否可用
     */
    public void validation(PayModeParam payModeParam, AlipayConfig alipayConfig){
        List<String> payWays = Optional.ofNullable(alipayConfig.getPayWays())
                .filter(StrUtil::isNotBlank)
                .map(s -> StrUtil.split(s, ','))
                .orElse(new ArrayList<>(1));
        // 发起的支付类型是否在支持的范围内
        PayWayEnum payWayEnum = Optional.ofNullable(AliPayWay.findByNo(payModeParam.getPayWay()))
                .orElseThrow(() -> new PayFailureException("非法的支付宝支付类型"));
        if (!payWays.contains(payWayEnum.getCode())) {
            throw new PayFailureException("该支付宝支付方式不可用");
        }
    }

    /**
     * 调起支付
     */
    public void pay(BigDecimal amount, Payment payment, AliPayParam aliPayParam, PayModeParam payModeParam, AlipayConfig alipayConfig){
        String payBody = null;
        // wap支付
        if (payModeParam.getPayWay()== PayWayCode.WAP){
            payBody = this.wapPay(amount, payment,alipayConfig,aliPayParam);
        }
        // 程序支付
        else if (payModeParam.getPayWay() == PayWayCode.APP){
            payBody = this.appPay(amount, payment, alipayConfig);
        }
        // pc支付
        else if (payModeParam.getPayWay() == PayWayCode.WEB){
            payBody = this.webPay(amount, payment, alipayConfig,aliPayParam);
        }
        // 二维码支付
        else if (payModeParam.getPayWay() == PayWayCode.QRCODE){
            payBody = this.qrCodePay(amount, payment, alipayConfig);
        }
        // 付款码支付
        else if (payModeParam.getPayWay() == PayWayCode.BARCODE){
            this.barCode(amount, payment,aliPayParam, alipayConfig);
        }

        // payBody到线程存储
        if (StrUtil.isNotBlank(payBody)) {
            AsyncPayInfo asyncPayInfo = new AsyncPayInfo()
                    .setPayBody(payBody);
            AsyncPayInfoLocal.set(asyncPayInfo);
        }
    }


    /**
     * wap支付
     */
    public String wapPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig,AliPayParam aliPayParam){

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setSubject(payment.getTitle());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setTotalAmount(amount.toPlainString());
        // 过期时间
//        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setProductCode(AliPayCode.QUICK_WAP_PAY);
//        model.setQuitUrl(aliPayParam.getReturnUrl());

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayParam.getReturnUrl());

        try {
            // 通过GET方式的请求, 返回URL的响应, 默认是POST方式的请求, 返回的是表单响应
            AlipayTradePagePayResponse response = AliPayApi.pageExecute(request, Method.GET.name());
            return response.getBody();
        }
        catch (AlipayApiException e) {
            log.error("支付宝手机支付失败", e);
            throw new PayFailureException("支付宝手机支付失败");
        }
    }

    /**
     * app支付
     */
    public String appPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        model.setSubject(payment.getTitle());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setTotalAmount(amount.toPlainString());

        try {
            AlipayTradeAppPayResponse response = AliPayApi.appPayToResponse(model, alipayConfig.getNotifyUrl());
            return response.getBody();
        }
        catch (AlipayApiException e) {
            log.error("支付宝APP支付失败", e);
            throw new PayFailureException("支付宝APP支付失败");
        }
    }

    /**
     * PC支付
     */
    public String webPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig, AliPayParam aliPayParam){

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        model.setSubject(payment.getTitle());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setTotalAmount(amount.toPlainString());
        // 目前仅支持FAST_INSTANT_TRADE_PAY
        model.setProductCode(AliPayCode.FAST_INSTANT_TRADE_PAY);

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayParam.getReturnUrl());
        try {
            // 通过GET方式的请求, 返回URL的响应, 默认是POST方式的请求, 返回的是表单响应
            AlipayTradePagePayResponse response = AliPayApi.pageExecute(request, Method.GET.name());
            return response.getBody();
        } catch (AlipayApiException e) {
            log.error("支付宝PC支付失败", e);
            throw new PayFailureException("支付宝PC支付失败");
        }
    }

    /**
     * 二维码支付(扫码支付)
     */
    public String qrCodePay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(payment.getTitle());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setTotalAmount(amount.toPlainString());

        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());

        try {
            AlipayTradePrecreateResponse response = AliPayApi.tradePrecreatePayToResponse(model, alipayConfig.getNotifyUrl());
            this.verifyErrorMsg(response);
            return response.getQrCode();
        } catch (AlipayApiException e) {
            log.error("支付宝手机支付失败", e);
            throw new PayFailureException("支付宝手机支付失败");
        }
    }

    /**
     * 付款码支付
     */
    public void barCode(BigDecimal amount, Payment payment, AliPayParam aliPayParam, AlipayConfig alipayConfig) {
        AlipayTradePayModel model = new AlipayTradePayModel();

        model.setSubject(payment.getTitle());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setScene("bar_code");
        model.setAuthCode(aliPayParam.getAuthCode());

        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setTotalAmount(amount.toPlainString());

        try {
            AlipayTradePayResponse response = AliPayApi.tradePayToResponse(model, alipayConfig.getNotifyUrl());

            // 支付成功处理 金额2000以下免密支付
            if (Objects.equals(response.getCode(),AliPayCode.SUCCESS)) {
                payment.setPayStatus(PayStatusCode.TRADE_SUCCESS)
                        .setPayTime(LocalDateTime.now());
                return;
            }
            // 非支付中响应码, 进行错误处理
            if (!Objects.equals(response.getCode(),AliPayCode.INPROCESS)){
                this.verifyErrorMsg(response);
            }
        } catch (AlipayApiException e) {
            log.error("主动扫码支付失败", e);
            throw new PayFailureException("主动扫码支付失败");
        }
    }

    /**
     * 验证错误信息
     */
    private void verifyErrorMsg(AlipayResponse alipayResponse){
        if (!Objects.equals(alipayResponse.getCode(), AliPayCode.SUCCESS)){
            String errorMsg = alipayResponse.getSubMsg();
            if (StrUtil.isBlank(errorMsg)){
                errorMsg = alipayResponse.getMsg();
            }
            log.error("支付失败 {}", errorMsg);
            throw new PayFailureException(errorMsg);
        }
    }
}
