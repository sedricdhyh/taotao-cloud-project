package com.taotao.cloud.message.biz.austin.common.dto.model;

import lombok.*;

/**
 *
 * 企业微信 应用消息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseWeChatContentModel extends ContentModel {

    /**
     * 下发企业微信消息的类型
     */
    private String sendType;

    /**
     * 文本消息 - 文案
     */
    private String content;

    /**
     * 图片媒体文件id
     */
    private String mediaId;

    /**
     *  其他消息类型： <a href="https://developer.work.weixin.qq.com/document/path/90372#%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF">https://developer.work.weixin.qq.com/document/path/90372#%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF</a>
     */


}
