package com.freesofts.example;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *@Author: 周伟
 *@CreateTime: 2023-02-21  14:23
 *@Version: 1.0
 */
@SpringBootTest
public class PinYinTests {

    @Test
    public void testPinYin(){
        String name = "我是周大伟";
        System.out.println(PinyinUtil.getPinyin(name));
        System.out.println("zhoudawei");
    }
}
