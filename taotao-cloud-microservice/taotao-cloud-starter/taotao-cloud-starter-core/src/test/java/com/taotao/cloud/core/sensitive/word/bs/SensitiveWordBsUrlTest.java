package com.taotao.cloud.core.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
public class SensitiveWordBsUrlTest {

    /**
     * 忽略中文繁简体
     */
    @Test
    public void commonUrlTest() {
        final String text = "点击链接 www.baidu.com查看答案";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[链接, www.baidu.com]", wordList.toString());

        Assert.assertEquals("点击** *************查看答案", SensitiveWordBs
                .newInstance().replace(text));
    }

    /**
     * 图片测试
     *
     * （1）可以检测
     * （2）默认不替换
     *
     */
    @Test
    public void imageUrlTest() {
        final String text = "双击查看大图 www.big-image.png查看";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[www.big-image.png]", wordList.toString());

        Assert.assertEquals(text, SensitiveWordBs.newInstance().replace(text));
    }

}
