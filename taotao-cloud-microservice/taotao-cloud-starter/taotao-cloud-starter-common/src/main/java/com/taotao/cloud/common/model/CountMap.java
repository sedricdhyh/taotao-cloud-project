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

package com.taotao.cloud.common.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.lang.Nullable;

/**
 * 用于计数的 Map
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 19:41:13
 */
public class CountMap {

	private final Map<Object, AtomicLong> data;

	public CountMap() {
		this(new HashMap<>(8));
	}

	/**
	 * 参数构造器，方便自定义数据承载，例如：ConcurrentMap
	 *
	 * @param data 集合
	 */
	public CountMap(Map<Object, AtomicLong> data) {
		this.data = data;
	}

	/**
	 * 添加数据
	 *
	 * @param value 数据
	 */
	public void add(Object value) {
		this.data.compute(value, (k, v) -> {
			if (v == null) {
				return new AtomicLong(1);
			} else {
				v.incrementAndGet();
				return v;
			}
		});
	}

	/**
	 * 递减计数
	 *
	 * @param value 数据
	 */
	public void decr(Object value) {
		this.data.computeIfPresent(value, (k, v) -> {
			v.decrementAndGet();
			return v;
		});
	}

	/**
	 * 删除计数
	 *
	 * @param value 数据
	 */
	public void remove(Object value) {
		this.data.remove(value);
	}

	/**
	 * 获取数据的 count
	 *
	 * @param value 数据
	 * @return count
	 */
	public long get(Object value) {
		AtomicLong counter = data.get(value);
		if (counter == null) {
			return 0L;
		}
		return counter.get();
	}

	/**
	 * 获取计数最大的数据
	 *
	 * @return 数据
	 */
	@Nullable
	@SuppressWarnings("unchecked")
	public <T> T getMax() {
		return (T) this.data.entrySet().stream()
			.max(Comparator.comparingLong(entry -> entry.getValue().longValue()))
			.map(Map.Entry::getKey)
			.orElse(null);
	}

	/**
	 * 获取计数最小的数据
	 *
	 * @return 数据
	 */
	@Nullable
	@SuppressWarnings("unchecked")
	public <T> T getMin() {
		return (T) this.data.entrySet().stream()
			.min(Comparator.comparingLong(entry -> entry.getValue().longValue()))
			.map(Map.Entry::getKey)
			.orElse(null);
	}

	/**
	 * 数据大小
	 *
	 * @return 返回数据大小
	 */
	public int size() {
		return this.data.keySet().size();
	}

	/**
	 * 重置，实际上是清空数据，方便复用
	 */
	public void clear() {
		this.data.clear();
	}

	@Override
	public String toString() {
		return this.data.toString();
	}

}
