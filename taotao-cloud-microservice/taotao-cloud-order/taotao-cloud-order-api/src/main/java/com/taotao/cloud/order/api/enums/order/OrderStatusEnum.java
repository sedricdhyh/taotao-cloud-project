package com.taotao.cloud.order.api.enums.order;

/**
 * 订单状态枚举
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 09:22:23
 */
public enum OrderStatusEnum {

	/**
	 * 订单状态
	 */
	UNPAID("未付款"),
	PAID("已付款"),
	UNDELIVERED("待发货"),
	DELIVERED("已发货"),
	COMPLETED("已完成"),
	/**
	 * 虚拟订单需要核验商品
	 */
	TAKE("待核验"),
	CANCELLED("已取消");

	private final String description;

	OrderStatusEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String description() {
		return this.description;
	}


}
