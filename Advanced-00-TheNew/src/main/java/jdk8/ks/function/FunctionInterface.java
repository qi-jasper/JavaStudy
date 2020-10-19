package jdk8.ks.function;

/**
 * @Description Java内置的四大核心函数式接口
 * @Author qi
 * @Date 2020/5/13 9:57
 * @ClassName FunctionInterface
 **/

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 消费型接口：Consumer<T>        void accept(T t)
 * 供给型接口：Supplier<T>        T get()
 * 函数型接口：Function<T, R>     R apply(T t)
 * 断定型接口：Predicate<T>       boolean test(T t)
 */
public class FunctionInterface {

    public List<String> filterString(List<String> list, Predicate<String> pre) {

        ArrayList<String> filterList = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }

        return filterList;
    }

    @Test
    public void test1() {
        List<String> list = Arrays.asList("北京", "天津", "南京", "普京", "静静");
        List<String> filterStr = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });

        System.out.println(filterStr);
    }
}
