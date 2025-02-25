package com.taotao.cloud.common.support.attr;


import java.util.Optional;
import java.util.Set;

/**
 * 属性上下文上下文
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:06:36
 */
public interface IAttributeContext {

	/**
	 * 设置属性
	 *
	 * @param key   key
	 * @param value 值
	 * @return {@link IAttributeContext }
	 * @since 2022-04-27 17:06:36
	 */
	IAttributeContext putAttr(final String key, final Object value);

	/**
	 * 获取配置属性
	 *
	 * @param key key
	 * @return {@link Object }
	 * @since 2022-04-27 17:06:36
	 */
	Object getAttr(final String key);

	/**
	 * 获取配置属性-Optional
	 *
	 * @param key key
	 * @return {@link Optional }<{@link Object }>
	 * @since 2022-04-27 17:06:36
	 */
	Optional<Object> getAttrOptional(final String key);

	/**
	 * 获取属性-字符串形式
	 *
	 * @param key key
	 * @return {@link String }
	 * @since 2022-04-27 17:06:36
	 */
	String getAttrString(final String key);

	/**
	 * 获取属性-Boolean
	 *
	 * @param key key
	 * @return {@link Boolean }
	 * @since 2022-04-27 17:06:36
	 */
	Boolean getAttrBoolean(final String key);

	/**
	 * 获取属性-Character
	 *
	 * @param key key
	 * @return {@link Character }
	 * @since 2022-04-27 17:06:36
	 */
	Character getAttrCharacter(final String key);

	/**
	 * 获取属性-Byte
	 *
	 * @param key key
	 * @return {@link Byte }
	 * @since 2022-04-27 17:06:36
	 */
	Byte getAttrByte(final String key);

	/**
	 * 获取属性-Short
	 *
	 * @param key key
	 * @return {@link Short }
	 * @since 2022-04-27 17:06:36
	 */
	Short getAttrShort(final String key);

	/**
	 * 获取属性-Integer
	 *
	 * @param key key
	 * @return {@link Integer }
	 * @since 2022-04-27 17:06:36
	 */
	Integer getAttrInteger(final String key);

	/**
	 * 获取属性-Float
	 *
	 * @param key key
	 * @return {@link Float }
	 * @since 2022-04-27 17:06:36
	 */
	Float getAttrFloat(final String key);

	/**
	 * 获取属性-Double
	 *
	 * @param key key
	 * @return {@link Double }
	 * @since 2022-04-27 17:06:36
	 */
	Double getAttrDouble(final String key);

	/**
	 * 获取属性-Long
	 *
	 * @param key key
	 * @return {@link Long }
	 * @since 2022-04-27 17:06:36
	 */
	Long getAttrLong(final String key);

	/**
	 * 移除属性
	 *
	 * @param key key
	 * @return {@link IAttributeContext }
	 * @since 2022-04-27 17:06:36
	 */
	IAttributeContext removeAttr(final String key);

	/**
	 * 是否包含 key
	 *
	 * @param key key
	 * @return boolean
	 * @since 2022-04-27 17:06:36
	 */
	boolean containsKey(final String key);

	/**
	 * 所有的 key 集合
	 *
	 * @return {@link Set }<{@link String }>
	 * @since 2022-04-27 17:06:36
	 */
	Set<String> keySet();

}
