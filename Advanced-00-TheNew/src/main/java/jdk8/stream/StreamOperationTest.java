package jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description Stream 的中间操作
 * @Author qi
 * @Date 2020/10/20 14:14
 * @ClassName StreamOperation
 **/

public class StreamOperationTest {

    /**
     * 筛选与切片
     */
    @Test
    public void filterTest() {
        List<String> list = new ArrayList<>();
        list.add("liu");
        list.add("qi");
        list.add("qi");
        list.add("jasper");
        list.add("ritchie");

        Stream<String> stream = list.stream();

        /**
         * filter()
         * 中间操作 + 终止操作：包含 "qi" 字符串的
         */
        stream.filter(e -> e.contains("qi")).forEach(System.out :: println);

        System.out.println("*******************************************");

        /**
         * limit()
         * 一旦执行了终止操作，就不能再使用，所以下执行方式会报错：
         * stream.limit(3).forEach(System.out :: println);
         * 所以每次执行前应当重新调用一下
         */
        list.stream().limit(3).forEach(System.out :: println);

        System.out.println("*******************************************");

        /**
         * skip()
         */
        list.stream().skip(2).forEach(System.out :: println);

        System.out.println("*******************************************");

        /**
         * distinct()
         */
        list.stream().distinct().forEach(System.out :: println);
    }

    /**
     * 映射
     */
    @Test
    public void mapTest() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        // 将所有字符大写
        list.stream().map(str -> str.toUpperCase()).forEach(System.out :: println);
    }

}
