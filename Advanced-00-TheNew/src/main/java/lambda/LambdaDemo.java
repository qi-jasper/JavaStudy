package lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * @Description Lambda使用举例
 * @Author qi
 * @Date 2020/5/12 22:01
 * @ClassName LambdaDemo
 **/

public class LambdaDemo {

    @Test
    public void lambdaTest() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Love Beijing");
            }
        };

        r1.run();

        System.out.println("*****************");

        Runnable r2 = () -> System.out.println("Love China!");
        r2.run();
    }

    @Test
    public void lambdaTest2() {

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(01, 02);
            }
        };

        int com1 = comparator.compare(12, 21);
        System.out.println(com1);

        System.out.println("*********************************");

        // Lambda表达式的写法
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int com2 = comparator2.compare(32, 21);
        System.out.println(com2);

        System.out.println("*********************************");

        // 方法引用的写法
        Comparator<Integer> comparator3 = Integer :: compare;
        int com3 = comparator3.compare(21, 21);
        System.out.println(com3);

    }
}