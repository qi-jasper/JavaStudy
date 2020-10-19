package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 1.举例：(o1, o2) -> Integer.compare(o1, o2)
 * 2.格式：
 *      -> : Lambda 操作符；左边称为形参列表（其实就是接口中的抽象方法的形参列表），右边称为Lambda体（其实就是重写的抽象方法的方法体）
 *
 * 3.关于 Lambda 表达式的使用（分为6种情况）
 * 4.Lambda表达式的本质：作为函数式接口的实例
 * 5.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 */
/**
 * @Description Lambda表达式的使用
 * @Author qi
 * @Date 2020/5/13 9:00
 * @ClassName UseLambda
 **/
public class UseLambdaTest {

    /**
     * 语法格式一：无参无返回值
     */
    @Test
    public void test1() {
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

    /**
     * 语法格式二：Lambda需要一个参数，但是没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("没有返回值");

        System.out.println("**************************");

        Consumer<String> con = (String s) -> {
            System.out.println(s);
        };

        con.accept("Lambda表达式没有返回值");

    }

    /**
     * 语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”
     */
    @Test
    public void test3() {
        Consumer<String> con = (s) -> {
            System.out.println(s);
        };

        con.accept("类型推断");

    }

    /**
     * 语法格式四：Lambda若只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test4() {
        Consumer<String> con = s -> {
            System.out.println(s);
        };

        con.accept("一个参数，小括号也可以省略");
    }

    /**
     * 语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test5() {
        Comparator<Integer> com = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);

            return o1.compareTo(o2);
        };

        System.out.println(com.compare(12, 21));
    }

    /**
     * 语法格式六：当Lambda体只有一条语句时，return与大括号若有，都可以省略
     */
    @Test
    public void test6() {
        Comparator<Integer> com = (o1, o2) ->  o1.compareTo(o2);
        System.out.println(com.compare(12, 21));
    }

}