package com.taotao.cloud.stream.framework.trigger.enums;

/**
 * 延时任务类型
 *
 */
public enum DelayTypeEnums {
    /**
     * 促销活动
     */
    PROMOTION("促销活动"),
    /**
     * 拼团订单
     */
    PINTUAN_ORDER("拼团订单"),
    /**
     * 直播
     */
    BROADCAST("直播");

    private String description;

    DelayTypeEnums(String description) {
        this.description = description;
    }

}
