package jdk8.method;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Description 方法引用测试
 * @Author qi
 * @Date 2020/5/13 12:41
 * @ClassName MethodReferences
 **/

public class MethodReferencesTest {

    /**
     * 情况一： 对象::实例方法名
     */
    @Test
    public void test1() {
        /**
         * Lambda 表达式写法
         */
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("qi");

        /**
         * 方法引用写法
         * PrintStream 中有一个方法：void println(T t)
         * 方法引用本质上就是 Lambda 表达式，而 Lambda 表达式作为函数式接口的实例
         * 所以方法引用也是函数式接口的实例
         */
        PrintStream out = System.out;
        Consumer<String> con2 = out::println;
        con2.accept("qi");
    }

    /**
     * 情况二：类::静态方法名
     */
    @Test
    public void test2() {
        /**
         * Lambda 表达式写法
         */
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(12, 21));

        /**
         * 方法引用写法
         */
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12, 3));
    }

    /**
     * 情况三：类::实例方法名
     * Comparator 中的 int compare(T t1, T t2)
     * String 中的 int t1.compareTo(t2)
     */
    @Test
    public void test3() {
        /**
         * Lambda 表达式写法
         */
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "a"));

        /**
         * 方法引用写法
         */
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("ab", "ab"));
    }
}