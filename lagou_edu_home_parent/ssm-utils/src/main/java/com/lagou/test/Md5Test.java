package com.lagou.test;

import com.lagou.utils.Md5;
import org.junit.Test;

public class Md5Test {

    @Test
    public void Md5Test() throws Exception {

        String md5 = Md5.md5("18512341234", Md5.md5key);
        System.out.println("Md5生成的密文密码是" + md5);
    }
}
