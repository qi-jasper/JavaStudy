package jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
