package com.taotao.cloud.message.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.message.biz.entity.ShortLink;
import java.util.List;

/**
 * 短链接 业务层
 */
public interface ShortLinkService extends IService<ShortLink> {

	/**
	 * 根据模型，查询返回的集合
	 *
	 * @param shortLink 短链接模型
	 * @return 端链接集合
	 */
	List<ShortLink> queryShortLinks(ShortLink shortLink);
}
