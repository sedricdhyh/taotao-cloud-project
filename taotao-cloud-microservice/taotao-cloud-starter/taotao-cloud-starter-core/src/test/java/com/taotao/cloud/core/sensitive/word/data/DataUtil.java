package com.taotao.cloud.core.sensitive.word.data;

import com.taotao.cloud.common.utils.collection.CollectionUtil;
import com.taotao.cloud.common.utils.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataUtil {

    /**
     * 获取对应文件的独一无二内容
     * @param name 名称
     * @return 结果
     */
    public static List<String> distinctLines(final String name) {
        final String dir = "D:\\github\\sensitive-word\\src\\main\\resources\\";
        final String path = dir + name;
        List<String> lines = FileUtil.readAllLines(path);
        return CollectionUtil.distinct(lines);
    }

    public static List<String> disctinctAndSort(final Collection<String> collection) {
        List<String> stringList = CollectionUtil.distinct(collection);
        Collections.sort(stringList);

        return stringList;
    }

    @Test
    @Ignore
    public void singleCharTest() {
        final String path = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";

        List<String> stringList = FileUtil.readAllLines(path);
        for(String s : stringList) {
            if(s.length() == 1) {
                System.out.println(s);
            }
        }
    }

}
