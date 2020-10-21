package jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description Stream 终止操作
 * @Author qi
 * @Date 2020/10/21 11:11
 * @ClassName StreamTerminalOperationTest
 **/

public class StreamTerminalOperationTest {

    /**
     * 匹配与查找
     */
    @Test
    public void matchAndSearchTest() {
        List<String> list = Arrays.asList("liu", "qi", "jasper", "hello");
        System.out.println(list.stream().allMatch(str -> str.equals("qi")));
        System.out.println(list.stream().anyMatch(str -> str.equals("hello")));
        System.out.println(list.stream().findFirst());
        System.out.println(list.stream().noneMatch(str -> str.startsWith("l")));
        System.out.println(list.stream().count());
        System.out.println(list.stream().findAny());
        System.out.println("********遍历********");
        list.stream().forEach(System.out :: println);
    }

    /**
     * 规约
     */
    @Test
    public void conventionTest() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         * reduce(T iden, BinaryOperator b)
         * 求 1~10 的和
         */
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /**
     * 收集
     */
    @Test
    public void collectTest() {
        List<String> list = Arrays.asList("liu", "qi", "jasper", "ritchie");
        List<String> getList = list.stream().filter(e -> e.contains("i")).collect(Collectors.toList());
        getList.forEach(System.out :: println);
    }
}
