package genericstudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 泛型测试类
 * @Author qi
 * @Date 2020/6/11 10:25
 * @ClassName Order
 **/

/**
 * 注意点：
 *      静态方法中不能使用类的泛型！！！
 *      异常类不能够使用泛型！！！
 *      如果声明泛型类的数组，需要先声明为Object类型，再强转：T[] array = (T[]) new Object[10];
 *      子类继承父类时，泛型的继承情况：
 *          class Father<T1, T2> {}
 *          // 子类不保留父类的泛型1；没有类型，擦除
 *          class Son<A, B> extends Father{}    // 等价于：class Son extends Father<Object, Object> {}
 *          // 子类不保留父类的泛型2；具体类型
 *          class Son<A, B> extends Father<Integer, String> {}
 *          // 子类保留父类的泛型1：全部保留
 *          class Son<T1, T2, A, B> extends Father<T1, T2> {}
 *          // 子类保留父类的泛型2：部分保留
 *          class Son<T2, A, B> extends Father<Integer, T2> {}
 */

public class GenericTest {

    @Test
    public void test1() {
        // 如果定义了泛型类，实例化没有指明泛型的类型，则认为此泛型类型为Object类型，不建议这么写！
        Order order = new Order();
        order.setOrderT(12);
        order.setOrderT("abc");

        // 建议实例化时指明类的泛型
        Order<String> dgc = new Order<String>("aa", 1, "bb");
        dgc.setOrderT("cc");

    }

    @Test
    public void test2() {
        SubOrder sub = new SubOrder();
        // 子类继承父类时，指明了泛型的类型，且子类自己没有携带泛型，所以就不需要再加泛型
        sub.setOrderT(1);
    }

    @Test
    public void test3() {
        // 子类继承父类时没有显示地指明泛型的类型，所以子类也保留了泛型，这里就需要指定泛型类型
        SubOrder1<String> sub = new SubOrder1<String>();
        sub.setOrderT("qi");
    }

    @Test
    public void test4() {
        /** 泛型不同的引用不能相互赋值
         * list1和list2之间不能相互赋值，即：
         * list1 = list2 不成立
         * list2 = list1 不成立
         */
        ArrayList<String> list1 = null;
        // 泛型只能是基本类型的包装类，不能是基本类型！！！
        ArrayList<Integer> list2 = null;

    }

    // 泛型方法测试
    @Test
    public void test5() {
        Order<String> od = new Order<String>();
        Integer[] arr = new Integer[] {1, 2, 3};
        List<Integer> list = od.copyFromArrayToList(arr);

        System.out.println(list);
    }
}
