/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.web.base.controller;

import com.taotao.cloud.web.base.entity.SuperEntity;
import com.taotao.cloud.web.base.service.BaseSuperService;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * SuperController
 * <p>
 * 继承该类，就拥有了如下方法： 1，page 分页查询，并支持子类扩展4个方法：handlerQueryParams、query、handlerWrapper、handlerResult
 * 2，save 保存，并支持子类扩展方法：handlerSave 3，update 修改，并支持子类扩展方法：handlerUpdate 4，delete
 * 删除，并支持子类扩展方法：handlerDelete 5，get 单体查询， 根据ID直接查询DB 6，list 列表查询，根据参数条件，查询列表 7，import
 * 导入，并支持子类扩展方法：handlerImport 8，export 导出，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * 9，preview 导出预览，并支持子类扩展3个方法：handlerQueryParams、query、handlerResult
 * <p>
 * 其中 page、export、preview 的查询条件一致，若子类重写了 handlerQueryParams、query、handlerResult 等任意方法，均衡受到影响
 * <p>
 * 若重写扩展方法无法满足，则可以重写page、save等方法，但切记不要修改 @RequestMapping 参数
 *
 * @param <S>         Service
 * @param <SaveDTO>   保存参数
 * @param <UpdateDTO> 修改参数
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 21:14:54¬
 */
public abstract class SuperController<S extends BaseSuperService<T, I>, T extends SuperEntity<T, I>, I extends Serializable, QueryDTO, SaveDTO, UpdateDTO, QueryVO>
	extends SimpleController<S, T, I> implements
	QueryController<T, I, QueryDTO, QueryVO>,
	SaveController<T, I, SaveDTO>,
	UpdateController<T, I, UpdateDTO>,
	DeleteController<T, I>,
	BatchController<T, I, SaveDTO, UpdateDTO>,
	ExcelController<T, I, QueryDTO, QueryVO> {

	private Class<QueryVO> queryVOClass;

	@Override
	@SuppressWarnings("unchecked")
	public Class<QueryVO> getQueryVOClass() {
		if (queryVOClass == null) {
			Type genericSuperclass = this.getClass().getGenericSuperclass();
			if (genericSuperclass instanceof ParameterizedType parameterizedType) {
				this.queryVOClass = (Class<QueryVO>) parameterizedType.getActualTypeArguments()[6];
			}
		}

		return queryVOClass;
	}
}
