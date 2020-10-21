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

public class StreamMidOperationTest {

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
     * 将字符串中的多个字符构成的集合转换为对应的 Stream 实例
     * @param str
     * @return
     */
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 映射
     */
    @Test
    public void mapTest() {
        /**
         * map()
         */
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        // 将所有字符大写
        list.stream().map(str -> str.toUpperCase()).forEach(System.out :: println);

        System.out.println("***************************");

        /**
         * flatMap()
         * 类似于 list 的 addAll() 方法
         */
        // 示例
        ArrayList list1 = new ArrayList<>();
        ArrayList list2 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        list1.add(list2);
        System.out.println(list1);

        list1.addAll(list2);
        System.out.println(list1);

        System.out.println("************stream**********stream************");
        /**
         * 类似 list.add() 中 list 里有一个 list
         * 相当于 stream 里有一个 stream
         */
        Stream<Stream<Character>> streamStream = list.stream().map(StreamMidOperationTest:: fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out :: println);
        });

        System.out.println("*****使用flatMap()******");
        // 使用 flatMap()
        Stream<Character> characterStream = list.stream().flatMap(StreamMidOperationTest::fromStringToStream);
        characterStream.forEach(System.out :: println);
    }

    /**
     * 排序
     */
    @Test
    public void sortTest() {
        /**
         * sort()：自然排序
         * 如果是自定义的一个带有对象泛型的 list，必须实现 Comparator 接口，定义排序规则
         * 否则使用 sorted(Comparator com)
         */
        List<Integer> list = Arrays.asList(12, 34, 3, 32, 1, -19);
        list.stream().sorted().forEach(System.out :: println);

        /**
         * sorted(Comparator com)：自定义排序
         */
        List<Integer> list1 = Arrays.asList(3, 1, 5, 2);
        list1.stream().sorted((e1, e2) -> Integer.compare(e1, e2)).forEach(System.out :: println);
        /*list1.stream().sorted((e1, e2) -> {
            return Integer.compare(e1, e2);
        }).forEach(System.out :: println);*/
    }

}
