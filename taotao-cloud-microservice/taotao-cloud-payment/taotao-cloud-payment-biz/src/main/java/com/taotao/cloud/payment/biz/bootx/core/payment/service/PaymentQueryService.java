package com.taotao.cloud.payment.biz.bootx.core.payment.service;

import cn.hutool.core.collection.CollectionUtil;
import com.taotao.cloud.common.model.PageParam;
import com.taotao.cloud.payment.biz.bootx.core.payment.dao.PaymentManager;
import com.taotao.cloud.payment.biz.bootx.dto.payment.PayChannelInfo;
import com.taotao.cloud.payment.biz.bootx.dto.payment.PaymentDto;
import com.taotao.cloud.payment.biz.bootx.param.payment.PaymentQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付单查询
 * @author xxm
 * @date 2021/6/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentQueryService {
    private final PaymentManager paymentManager;

    /**
     * 根据支付Id查询支付单
     */
    public PaymentDto findById(Long id){
        return paymentManager.findById(id)
                .map(Payment::toDto)
                .orElseThrow(DataNotExistException::new);
    }

    /**
     * 根据业务ID获取成功记录
     */
    public List<PaymentDto> findByBusinessId(String businessId){
        return paymentManager.findByBusinessIdDesc(businessId).stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据业务ID获取支付状态
     */
    public Integer findStatusByBusinessId(String businessId){
        // 根据订单查询支付记录
        List<Payment> payments = paymentManager.findByBusinessIdNoCancelDesc(businessId);
        if (!CollectionUtil.isEmpty(payments)) {
            Payment payment = payments.get(0);
            return payment.getPayStatus();
        }
        return -1;
    }

    /**
     * 根据businessId获取订单支付方式
     */
    public List<PayChannelInfo> findPayTypeInfoByBusinessId(String businessId){
        List<Payment> payments = paymentManager.findByBusinessIdDesc(businessId);
        return payments.stream()
                .findFirst()
                .map(Payment::getPayChannelInfoList)
                .orElse(new ArrayList<>(1));
    }

    /**
     * 根据id获取订单支付方式
     */
    public List<PayChannelInfo> findPayTypeInfoById(Long id){
        return paymentManager.findById(id)
                .map(Payment::getPayChannelInfoList)
                .orElse(new ArrayList<>(1));
    }

    /**
     * 根据用户id查询
     */
    public List<PaymentDto> findByUser(Long userId){
        return paymentManager.findByUserId(userId).stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 分页
     */
    public PageResult<PaymentDto> page(PageParam pageParam, PaymentQuery param, OrderParam orderParam){
        return MpUtil.convert2DtoPageResult(paymentManager.page(pageParam,param,orderParam));
    }

    /**
     * 超级查询
     */
    public PageResult<PaymentDto> superPage(PageParam pageParam, QueryParams queryParams){
        return MpUtil.convert2DtoPageResult(paymentManager.superPage(pageParam,queryParams));
    }
}
