package com.taotao.cloud.demo.utils;

import com.taotao.cloud.common.utils.lang.ObjectUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * ObjectUtil Tester.
 *
 */
public class ObjectUtilTest {

	/**
	 * Method: isNull(@Nullable Object object)
	 */
	@Test
	public void testIsNull() throws Exception {
		Assert.assertTrue(ObjectUtil.isNull(null));
	}

	/**
	 * Method: isNotNull(@Nullable Object object)
	 */
	@Test
	public void testIsNotNull() throws Exception {
		Assert.assertFalse(ObjectUtil.isNotNull(null));
	}

	/**
	 * Method: isTrue(@Nullable Boolean object)
	 */
	@Test
	public void testIsTrue() throws Exception {
		Assert.assertTrue(ObjectUtil.isTrue(true));
		Assert.assertTrue(ObjectUtil.isTrue(Boolean.TRUE));
		Assert.assertFalse(ObjectUtil.isTrue(null));
		Assert.assertFalse(ObjectUtil.isTrue(false));
		Assert.assertFalse(ObjectUtil.isTrue(Boolean.FALSE));
	}

	/**
	 * Method: isFalse(@Nullable Boolean object)
	 */
	@Test
	public void testIsFalse() throws Exception {
		Assert.assertTrue(ObjectUtil.isFalse(null));
		Assert.assertTrue(ObjectUtil.isFalse(false));
		Assert.assertTrue(ObjectUtil.isFalse(Boolean.FALSE));
		Assert.assertFalse(ObjectUtil.isFalse(true));
		Assert.assertFalse(ObjectUtil.isFalse(Boolean.TRUE));
	}

	/**
	 * Method: isNotEmpty(@Nullable Object[] array)
	 */
	@Test
	public void testIsNotEmpty() throws Exception {
		Assert.assertFalse(ObjectUtil.isNotEmpty(null));
		Assert.assertFalse(ObjectUtil.isNotEmpty(new Object[0]));
		Assert.assertFalse(ObjectUtil.isNotEmpty(Collections.emptyList()));
	}

	/**
	 * Method: isNotEmpty(@Nullable Object obj)
	 */
	@Test
	public void testIsEmptyObj() throws Exception {
		Assert.assertTrue(ObjectUtil.isEmpty(null));
		Assert.assertTrue(ObjectUtil.isEmpty(new Object[0]));
		Assert.assertTrue(ObjectUtil.isEmpty(Collections.emptyList()));
	}

	/**
	 * Method: toBoolean(@Nullable Object obj)
	 */
	@Test
	public void testToBoolean() throws Exception {
		Assert.assertNull(ObjectUtil.toBoolean(null));
		Assert.assertNull(ObjectUtil.toBoolean("a"));
		Assert.assertTrue(ObjectUtil.toBoolean("1"));
		Assert.assertTrue(ObjectUtil.toBoolean(null, true));
		Assert.assertTrue(ObjectUtil.toBoolean("a", true));
		Assert.assertTrue(ObjectUtil.toBoolean("1", true));
	}

}
