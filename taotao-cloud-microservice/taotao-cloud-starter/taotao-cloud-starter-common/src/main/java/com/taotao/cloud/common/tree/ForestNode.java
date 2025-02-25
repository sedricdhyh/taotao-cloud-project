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
package com.taotao.cloud.common.tree;

import java.util.List;
import java.util.Objects;

/**
 * 森林节点
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-03 08:05:01
 */
public class ForestNode extends TreeNode {

	private static final long serialVersionUID = -5188222097134746118L;

	/**
	 * 节点内容
	 */
	private Object content;

	public ForestNode(Long id, Long parentId, Object content) {
		this.id = id;
		this.parentId = parentId;
		this.content = content;
	}

	public ForestNode(Object content) {
		this.content = content;
	}

	public ForestNode(Long id, Long parentId, List<INode> children,
		Boolean hasChildren, Object content, Integer sort) {
		super(id, parentId, children, hasChildren,sort);
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ForestNode{" +
			"content=" + content +
			"} " + super.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		ForestNode that = (ForestNode) o;
		return Objects.equals(content, that.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), content);
	}
}
