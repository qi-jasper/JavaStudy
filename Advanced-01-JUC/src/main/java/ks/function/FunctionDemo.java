package ks.function;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/5 19:42
 * @ClassName FunctionDemo
 **/

public class FunctionDemo {

    /**
     * 函数式接口
     */
    @Test
    public void functionInterfaceTest() {
        // 工具类：输出输入的值
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };

        // 只要是函数型接口，就可以用Lambda表达式简化
        Function<String, String> func = (str) -> {return str;};

        System.out.println(function.apply("qi"));
        System.out.println(func.apply("hello!qi"));

    }


    /**
     * 断定型接口，有一个输入参数，则返回值为布尔值。
     */
    @Test
    public void predicateInterfaceTest() {
        // 判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };

        // 简化
        Predicate<String> pre = (str) -> { return str.isEmpty(); };

        System.out.println(predicate.test("qi"));
        System.out.println(pre.test("hello!qi"));

    }

    /**
     * Consumer 消费型接口
     */
    @Test
    public void consumerInterfaceTest() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // 简化为Lambda
        Consumer<String> cs = (s) -> {
            System.out.println(s);
        };

        // 打印字符串
        consumer.accept("qi");
        cs.accept("hello!qi");
    }


    /**
     * Supplier 供给型接口
     */
    @Test
    public void supplierInterfaceTest() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };

        // 写成Lambda表达式形式
        Supplier<Integer> supp = () -> { return 1024; };

        System.out.println(supplier.get());
        System.out.println(supp.get());
    }

}
