package com.taotao.cloud.promotion.api.enums;

/**
 * 促销适用范围类型枚举
 *
 *
 */
public enum PromotionsScopeTypeEnum {

    /**
     * 枚举
     */
    ALL("全品类"),
    PORTION_GOODS_CATEGORY("部分商品分类"),
    PORTION_SHOP_CATEGORY("部分店铺分类"),
    PORTION_GOODS("指定商品");

    private final String description;

    PromotionsScopeTypeEnum(String str) {
        this.description = str;
    }

    public String description() {
        return description;
    }
}
