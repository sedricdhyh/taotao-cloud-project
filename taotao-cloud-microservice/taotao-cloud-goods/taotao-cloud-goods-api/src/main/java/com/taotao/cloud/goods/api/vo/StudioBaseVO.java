package com.taotao.cloud.goods.api.vo;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.io.Serial;
import java.io.Serializable;


/**
 * 小程序直播间基础哦
 *
 * @since 2021/5/17 9:47 上午
 */
@RecordBuilder
public record StudioBaseVO(
	/**
	 * 直播间名字，最短3个汉字，最长17个汉字，1个汉字相当于2个字符
	 */
	String name,

	/**
	 * 背景图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档： <a href="https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html">https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html</a>；直播间背景图，图片规则：建议像素1080*1920，大小不超过2M
	 */
	String coverImg,

	/**
	 * 直播计划开始时间（开播时间需要在当前时间的10分钟后 并且 开始时间不能在 6 个月后）
	 */
	String startTime,

	/**
	 * 直播计划结束时间（开播时间和结束时间间隔不得短于30分钟，不得超过24小时）
	 */
	String endTime,

	/**
	 * 主播昵称，最短2个汉字，最长15个汉字，1个汉字相当于2个字符
	 */
	String anchorName,

	/**
	 * 主播微信号，如果未实名认证，需要先前往“小程序直播”小程序进行实名验证, 小程序二维码链接：<a href="https://res.wx.qq.com/op_res/9rSix1dhHfK4rR049JL0PHJ7TpOvkuZ3mE0z7Ou_Etvjf-w1J_jVX0rZqeStLfwh">https://res.wx.qq.com/op_res/9rSix1dhHfK4rR049JL0PHJ7TpOvkuZ3mE0z7Ou_Etvjf-w1J_jVX0rZqeStLfwh</a>
	 */
	String anchorWechat,

	/**
	 * 分享图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档： <a href="https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html">https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html</a>；直播间分享图，图片规则：建议像素800*2550，大小不超过1M；
	 */
	String shareImg,

	/**
	 * 购物直播频道封面图，填入mediaID（mediaID获取后，三天内有效）；图片mediaID的获取，请参考以下文档： <a href="https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html">https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html</a>,
	 * 购物直播频道封面图，图片规则：建议像素800*800，大小不超过100KB；
	 */
	String feedsImg,

	/**
	 * 回放视频链接
	 */
	String mediaUrl,

	/**
	 * 房间ID
	 */
	Integer roomId,

	/**
	 * 小程序直播码
	 */
	String qrCodeUrl,

	/**
	 * 店铺ID
	 */
	Long storeId,

	/**
	 * 直播间商品数量
	 */
	Integer roomGoodsNum,

	/**
	 * 直播间商品(最多展示两个商品：name/goodsImage)
	 */
	String roomGoodsList,

	/**
	 * 推荐直播间
	 */
	Boolean recommend,

	/**
	 * 直播间状态
	 */
	String status
) implements Serializable {

	@Serial
	private static final long serialVersionUID = 3829199991161122317L;


}
