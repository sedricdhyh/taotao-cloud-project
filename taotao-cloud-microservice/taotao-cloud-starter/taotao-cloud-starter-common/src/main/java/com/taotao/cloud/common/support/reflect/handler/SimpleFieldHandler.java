package com.taotao.cloud.common.support.reflect.handler;


import com.taotao.cloud.common.support.handler.IHandler;
import com.taotao.cloud.common.support.reflect.simple.SimpleField;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 字段处理类
 */
public class SimpleFieldHandler implements IHandler<Field, SimpleField> {

	@Override
	public SimpleField handle(Field field) {
		SimpleField simpleField = new SimpleField();
		simpleField.field(field);
		simpleField.name(field.getName());
		simpleField.fullName(field.getName());
		simpleField.type(field.getType());
		simpleField.annotations(Arrays.asList(field.getAnnotations()));
		simpleField.access(field.getModifiers());
		return simpleField;
	}

}
