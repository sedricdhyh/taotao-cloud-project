package com.taotao.cloud.core.sensitive.sensitive.core.strategy;

import com.taotao.cloud.core.sensitive.sensitive.core.util.strategy.SensitiveStrategyUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 脱敏策略工具类测试

 */
public class SensitiveStrategyUtilTest {

    @Test
    public void passwordTest() {
        final String password = "123456";
        final String sensitive = SensitiveStrategyUtil.password(password);
        Assert.assertNull(sensitive);
    }

    @Test
    public void chineseNameTest() {
        final String chineseName = "张三丰";
        final String sensitive = SensitiveStrategyUtil.chineseName(chineseName);
        Assert.assertEquals("张*丰", sensitive);
    }

    @Test
    public void phoneTest() {
        final String phone = "13012347894";
        final String sensitive = SensitiveStrategyUtil.phone(phone);
        Assert.assertEquals("130****7894", sensitive);
    }

    @Test
    public void emailTest() {
        final String email = "123456@gmail.com";
        final String sensitive = SensitiveStrategyUtil.email(email);
        Assert.assertEquals("123***@gmail.com", sensitive);
    }

    @Test
    public void cardIdTest() {
        final String cardId = "1234888888888888884321";
        final String sensitive = SensitiveStrategyUtil.cardId(cardId);
        Assert.assertEquals("123488**********884321", sensitive);
    }

}
