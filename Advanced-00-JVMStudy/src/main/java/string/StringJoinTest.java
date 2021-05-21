package string;

import org.junit.Test;

/**
 * @Description 字符串拼接测试
 * @Author qi
 * @Date 2021/5/19 13:55
 * @ClassName StringJoinTest
 **/
public class StringJoinTest {

    @Test
    public void test1() {
        // 也是用的常量池中 s2 的地址
        String s1 = "a" + "b" + "c";
        // 一定放在字符串常量池中的，将此地址赋给 s2
        String s2 = "abc";

        /**
         * 最终 .java 编译成 .class ，再执行 .class
         * String s1 = "abc"
         * String s2 = "abc
         */
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    @Test
    public void test2() {
        String s1 = "JavaEE";
        String s2 = "Hadoop";

        String s3 = "JavaEEHadoop";
        String s4 = "JavaEE" + "Hadoop";
        String s5 = s1 + "Hadoop";
        String s6 = "JavaEE" +s2;
        String s7 = s1 + s2;

        /**
         * intern(): 判断字符串常量池中是否存在 JavaHadoop 这个值，如果存在，则返回该字符串地址
         * 如果不存在，则在常量池中加载一份，并返回该地址
         */
        String s8 = s6.intern();

        // true
        System.out.println(s3 == s4);
        // false
        System.out.println(s3 == s5);
        // false
        System.out.println(s3 == s6);
        // false
        System.out.println(s3 == s7);
        // false
        System.out.println(s5 == s6);
        // false
        System.out.println(s5 == s7);
        // false
        System.out.println(s6 == s7);
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        /**
         * s1 + s2 执行细节：
         * - StringBuilder s = new StringBuilder();
         * s.append("a");
         * s.append("b");
         * s.toString() --> 约等于 new String("ab");
         */
        String s4 = s1 + s2;
        // false
        System.out.println(s3 == s4);
    }

    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        // true
        System.out.println(s3 == s4);
    }
}