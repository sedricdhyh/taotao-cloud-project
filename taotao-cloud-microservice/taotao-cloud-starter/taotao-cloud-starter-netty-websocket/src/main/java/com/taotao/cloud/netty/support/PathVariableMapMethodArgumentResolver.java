package com.taotao.cloud.netty.support;


import static com.taotao.cloud.netty.pojo.PojoEndpointServer.URI_TEMPLATE;

import com.taotao.cloud.netty.annotation.PathVariable;
import io.netty.channel.Channel;
import java.util.Collections;
import java.util.Map;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class PathVariableMapMethodArgumentResolver implements MethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		PathVariable ann = parameter.getParameterAnnotation(PathVariable.class);
		return (ann != null && Map.class.isAssignableFrom(parameter.getParameterType()) &&
			!StringUtils.hasText(ann.value()));
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, Channel channel, Object object)
		throws Exception {
		PathVariable ann = parameter.getParameterAnnotation(PathVariable.class);
		String name = ann.name();
		if (name.isEmpty()) {
			name = parameter.getParameterName();
			if (name == null) {
				throw new IllegalArgumentException(
					"Name for argument type [" + parameter.getNestedParameterType().getName() +
						"] not available, and parameter name information not found in class file either.");
			}
		}
		Map<String, String> uriTemplateVars = channel.attr(URI_TEMPLATE).get();
		if (!CollectionUtils.isEmpty(uriTemplateVars)) {
			return uriTemplateVars;
		} else {
			return Collections.emptyMap();
		}
	}
}
