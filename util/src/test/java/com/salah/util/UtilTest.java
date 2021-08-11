package com.salah.util;

import org.junit.Test;

/**
 * @author Salah
 * @date 2021/8/10
 */
public class UtilTest {

    @Test
    public void test() {
        String path1 = UtilTest.class.getClassLoader().getResource("").getPath();
        String path2 = UtilTest.class.getClassLoader().getResource(".").getPath();
        String path3 = UtilTest.class.getClassLoader().getResource("").getPath();
        /**
         * getClassLoader() 不能使用 "/"
         * /C:/Projects/github_proj/coding/util/target/test-classes/
         * /C:/Projects/github_proj/coding/util/target/test-classes/
         * /C:/Projects/github_proj/coding/util/target/test-classes/
         */
        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);

        String path11 = UtilTest.class.getResource("").getPath();
        String path22 = UtilTest.class.getResource(".").getPath();
        String path33 = UtilTest.class.getResource("/").getPath();
        /**
         * /C:/Projects/github_proj/coding/util/target/test-classes/com/salah/util/
         * /C:/Projects/github_proj/coding/util/target/test-classes/com/salah/util/
         * /C:/Projects/github_proj/coding/util/target/test-classes/
         */
        System.out.println(path11);
        System.out.println(path22);
        System.out.println(path33);
    }
}
