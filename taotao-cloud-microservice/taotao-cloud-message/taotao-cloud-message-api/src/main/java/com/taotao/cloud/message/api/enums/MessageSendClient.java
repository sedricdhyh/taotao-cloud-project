package com.taotao.cloud.message.api.enums;

/**
 * 消息发送客户端
 */
public enum MessageSendClient {

    //全部用户
    MEMBER("会员"),
    //指定用户
    STORE("店铺");

    private final String description;

    MessageSendClient(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
