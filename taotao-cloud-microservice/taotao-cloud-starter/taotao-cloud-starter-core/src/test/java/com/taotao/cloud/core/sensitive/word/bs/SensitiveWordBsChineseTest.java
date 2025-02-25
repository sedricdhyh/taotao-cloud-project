package com.taotao.cloud.core.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SensitiveWordBsChineseTest {

    /**
     * 忽略中文繁简体
     */
    @Test
    public void ignoreChineseStyleTest() {
        final String text = "我爱我的祖国和五星紅旗。";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[祖国, 五星紅旗]", wordList.toString());
    }

}
