package com.taotao.cloud.order.api.enums.order;

/**
 * 订单的投诉状态
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 09:22:03
 */
public enum OrderComplaintStatusEnum {

	/**
	 * 新订单，不能申请投诉
	 */
	NEW("待审核"),
	/**
	 * 未申请
	 */
	NO_APPLY("未申请"),
	/**
	 * 申请中
	 */
	APPLYING("申请中"),
	/**
	 * 已完成
	 */
	COMPLETE("已完成"),
	/**
	 * 已失效
	 */
	EXPIRED("已失效"),
	/**
	 * 取消
	 */
	CANCEL("取消");

	private final String description;

	OrderComplaintStatusEnum(String description) {
		this.description = description;
	}

	public String description() {
		return this.description;
	}


}
