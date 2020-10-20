package jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description Stream 的创建方式
 * @Author qi
 *
 * @Date 2020/10/20 11:36
 * @ClassName StreamTest
 **/

public class StreamCreateTest {

    /**
     * 创建 Stream 的方式一：通过集合
     * Java 8 中的 Collection 接口被拓展，提供了两个获取流的方法：
     * default Stream<E> stream(): 返回一个顺序流
     * default Stream<E> parallelStream(): 返回一个并行流
     */
    @Test
    public void byCollection() {
        List<String> list = new ArrayList<>();
        list.add("liu");
        list.add("qi");
        list.add("jasper");
        list.add("ritchie");

        // 获取一个顺序流对象
        Stream<String> stream = list.stream();

        // 获取一个并行流对象
        Stream<String> parallelStream = list.parallelStream();
    }

    /**
     * 创建 Stream 的方式二：通过数组
     */
    @Test
    public void byArray() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(arr);
    }

    /**
     * 创建 Stream 的方式三：通过 Stream 的 of()
     */
    @Test
    public void byStreamOf() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    /**
     * 创建 Stream 的方式四、创建无限流
     */
    @Test
    public void byInfinite() {
        // 迭代，无限生成； limit(10) 表示只要前10个
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out :: println);

        // 生成
        Stream.generate(Math :: random).limit(10).forEach(System.out :: println);
    }
}
