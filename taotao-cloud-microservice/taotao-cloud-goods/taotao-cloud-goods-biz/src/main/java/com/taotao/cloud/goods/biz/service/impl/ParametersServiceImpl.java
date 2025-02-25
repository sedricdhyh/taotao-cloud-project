package com.taotao.cloud.goods.biz.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.goods.api.dto.GoodsParamsDTO;
import com.taotao.cloud.goods.api.dto.GoodsParamsDTOBuilder;
import com.taotao.cloud.goods.api.dto.GoodsParamsItemDTO;
import com.taotao.cloud.goods.api.dto.GoodsParamsItemDTOBuilder;
import com.taotao.cloud.goods.biz.entity.Goods;
import com.taotao.cloud.goods.biz.entity.Parameters;
import com.taotao.cloud.goods.biz.mapper.IParametersMapper;
import com.taotao.cloud.goods.biz.service.IGoodsService;
import com.taotao.cloud.goods.biz.service.IParametersService;
import com.taotao.cloud.stream.framework.rocketmq.RocketmqSendCallbackBuilder;
import com.taotao.cloud.stream.framework.rocketmq.tags.GoodsTagsEnum;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品参数业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:52
 */
@AllArgsConstructor
@Service
public class ParametersServiceImpl extends ServiceImpl<IParametersMapper, Parameters> implements
	IParametersService {

	/**
	 * 商品服务
	 */
	private final IGoodsService goodsService;

	private final RocketmqCustomProperties rocketmqCustomProperties;
	private final RocketMQTemplate rocketMQTemplate;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateParameter(Parameters parameters) {
		Parameters origin = this.getById(parameters.getId());
		if (origin == null) {
			throw new BusinessException(ResultEnum.CATEGORY_NOT_EXIST);
		}

		List<String> goodsIds = new ArrayList<>();
		LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.select(Goods::getId, Goods::getParams);
		queryWrapper.like(Goods::getParams, parameters.getGroupId());
		List<Map<String, Object>> goodsList = this.goodsService.listMaps(queryWrapper);

		if (!goodsList.isEmpty()) {
			for (Map<String, Object> goods : goodsList) {
				String params = (String) goods.get("params");
				List<GoodsParamsDTO> goodsParamsDTOS = JSONUtil.toList(params,
					GoodsParamsDTO.class);
				List<GoodsParamsDTO> goodsParamsDTOList = goodsParamsDTOS.stream()
					.filter(i -> i.groupId() != null && i.groupId().equals(parameters.getGroupId()))
					.toList();
				goodsParamsDTOList = this.setGoodsItemDTOList(goodsParamsDTOList, parameters);

				this.goodsService.updateGoodsParams(Convert.toLong(goods.get("id")),
					JSONUtil.toJsonStr(goodsParamsDTOS));
				goodsIds.add(goods.get("id").toString());
			}

			String destination = rocketmqCustomProperties.getGoodsTopic() + ":"
				+ GoodsTagsEnum.UPDATE_GOODS_INDEX.name();
			//发送mq消息
			rocketMQTemplate.asyncSend(destination, JSONUtil.toJsonStr(goodsIds),
				RocketmqSendCallbackBuilder.commonCallback());
		}
		return this.updateById(parameters);
	}

	/**
	 * 更新商品参数信息
	 *
	 * @param goodsParamsDTOList 商品参数项列表
	 * @param parameters         参数信息
	 */
	private List<GoodsParamsDTO> setGoodsItemDTOList(List<GoodsParamsDTO> goodsParamsDTOList,
													 Parameters parameters) {
		List<GoodsParamsDTO> newGoodsParamsDTOList = new ArrayList<>();

		for (GoodsParamsDTO goodsParamsDTO : goodsParamsDTOList) {
			List<GoodsParamsItemDTO> goodsParamsItemDTOList = goodsParamsDTO.goodsParamsItemDTOList()
				.stream()
				.filter(i -> i.paramId() != null && i.paramId().equals(parameters.getId()))
				.toList();

			List<GoodsParamsItemDTO> newGoodsParamsItemDTOList = new ArrayList<>();
			for (GoodsParamsItemDTO goodsParamsItemDTO : goodsParamsItemDTOList) {
				newGoodsParamsItemDTOList.add(this.setGoodsItemDTO(goodsParamsItemDTO, parameters));
			}
			newGoodsParamsDTOList.add(GoodsParamsDTOBuilder.builder(goodsParamsDTO).goodsParamsItemDTOList(newGoodsParamsItemDTOList).build());
		}
		return newGoodsParamsDTOList;
	}

	/**
	 * 更新商品参数详细信息
	 *
	 * @param goodsParamsItemDTO 商品参数项信息
	 * @param parameters         参数信息
	 */
	private GoodsParamsItemDTO setGoodsItemDTO(GoodsParamsItemDTO goodsParamsItemDTO, Parameters parameters) {
		if (goodsParamsItemDTO.paramId().equals(parameters.getId())) {

			GoodsParamsItemDTOBuilder builder = GoodsParamsItemDTOBuilder.builder()
				.paramId(parameters.getId())
				.paramName(parameters.getParamName())
				.required(parameters.getRequired())
				.isIndex(parameters.getIsIndex())
				.sort(parameters.getSort());

			if (CharSequenceUtil.isNotEmpty(parameters.getOptions()) && CharSequenceUtil.isNotEmpty(goodsParamsItemDTO.paramValue()) && !parameters.getOptions().contains(goodsParamsItemDTO.paramValue())) {
				if (parameters.getOptions().contains(",")) {
					builder.paramValue(parameters.getOptions().substring(0, parameters.getOptions().indexOf(",")));
				} else {
					builder.paramValue(parameters.getOptions());
				}
			}
			goodsParamsItemDTO = builder.build();
		}
		return goodsParamsItemDTO;
	}

}
