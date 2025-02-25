package com.taotao.cloud.order.biz.roketmq.event.impl;

import com.egzosn.pay.paypal.bean.order.Payment;
import com.taotao.cloud.common.utils.common.IdGeneratorUtil;
import com.taotao.cloud.common.utils.context.ContextUtil;
import com.taotao.cloud.common.utils.log.LogUtil;
import com.taotao.cloud.order.api.message.OrderMessage;
import com.taotao.cloud.order.api.enums.order.PayStatusEnum;
import com.taotao.cloud.order.biz.entity.order.Order;
import com.taotao.cloud.order.biz.roketmq.event.OrderStatusChangeEvent;
import com.taotao.cloud.order.biz.service.order.IOrderService;
import com.taotao.cloud.payment.api.enums.PaymentMethodEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.taotao.cloud.order.api.enums.order.OrderStatusEnum.CANCELLED;

/**
 * 支付
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-05-19 15:03:55
 */
@Service
public class PaymentExecute implements OrderStatusChangeEvent {

	/**
	 * 订单
	 */
	@Autowired
	private IOrderService orderService;

	@Override
	public void orderChange(OrderMessage orderMessage) {
		switch (orderMessage.newStatus()) {
			case CANCELLED:
				Order order = orderService.getBySn(orderMessage.orderSn());

				//如果未付款，则不去要退回相关代码执行
				if (order.getPayStatus().equals(PayStatusEnum.UNPAID.name())) {
					return;
				}
				PaymentMethodEnum paymentMethodEnum = PaymentMethodEnum.valueOf(
					order.getPaymentMethod());
				//进行退款操作
				switch (paymentMethodEnum) {
					case WALLET:
					case ALIPAY:
					case WECHAT:
						//获取支付方式
						Payment payment =
							(Payment) ContextUtil.getBean(paymentMethodEnum.getPlugin(), true);

						RefundLog refundLog = RefundLog.builder()
							.isRefund(false)
							.totalAmount(order.getFlowPrice())
							.payPrice(order.getFlowPrice())
							.memberId(order.getMemberId())
							.paymentName(order.getPaymentMethod())
							.afterSaleNo("订单取消")
							.orderSn(order.getSn())
							.paymentReceivableNo(order.getReceivableNo())
							.outOrderNo("AF" + IdGeneratorUtil.getIdStr())
							.outOrderNo("AF" + IdGeneratorUtil.getIdStr())
							.refundReason("订单取消")
							.build();
						payment.cancel(refundLog);
						break;
					case BANK_TRANSFER:
						break;
					default:
						LogUtil.error("订单支付执行异常,订单编号：{}", orderMessage.orderSn());
						break;
				}
				break;
			default:
				break;
		}
	}
}
