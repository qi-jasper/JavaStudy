package getclass;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description 调用运行时类指定的属性、方法、构造器等
 * @Author qi
 * @Date 2020/9/29 14:02
 **/

public class GetSpecifiedTest {

    /**
     * 通过 getField() 方法获取运行时类指定的属性
     * 通常不使用这个方法获取
     */
    @Test
    public void getSpecifiedField() throws Exception {
        Class<Man> manClass = Man.class;

        /**
         * 创建运行时类的对象
         * 因为非静态的属性赋值和方法调用都依托于对象，所以先创建一个
         */
        Man man = manClass.newInstance();

        // 获取为 Man 类中为 public 的 id 属性
        Field id = manClass.getField("id");

        // 根据对象 man 设置 id 的值
        id.set(man, 1001);
        // 根据对象 man 获取 id 的值
        int mId = (int) id.get(man);
        System.out.println(mId);

        // 获取 Man 类中为默认权限的 age 属性，报错，因为 getField() 只能获取 public 的属性
        Field age = manClass.getField("age");
        age.set(man, 10);
        int mAge = (int) age.get(man);
        System.out.println(mAge);

    }

    /**
     * getDeclaredField() 方法获取 Man 类中声明的指定对象
     */
    @Test
    public void getSpecifiedField2() throws Exception {
        Class<Man> manClass = Man.class;
        Man man = manClass.newInstance();

        Field name = manClass.getDeclaredField("name");
        // 因为 name 是 private ，通过 setAccessible(true) 可以使其修改
        name.setAccessible(true);

        name.set(man, "tom");
        System.out.println(name.get(man));
    }

    /**
     * getMethod() 获取方法，用法和上面类似
     * getDeclaredMethod() 方法获取 Man 类中声明的方法
     */
    @Test
    public void getSpecifiedMethod() throws Exception {
        Class<Man> manClass = Man.class;

        Man man = manClass.newInstance();

        // 参数除了传方法的名称以外，还可以通过传递方法的形参来指定调用的具体哪个方法
        Method compareTo = manClass.getDeclaredMethod("compareTo", String.class);
        // 如果是私有的方法要执行该方法
        compareTo.setAccessible(true);
        // 非静态方法，通过 man 对象调用，如普通方法调用一样，传递一个给形参赋值的实参
        compareTo.invoke(man, "hello");

        // invoke() 方法的返回值即为调用方法的返回值，即调用的 compareTo() 方法的返回值
        Object hello = compareTo.invoke(man, "hello");
        System.out.println(hello);

        System.out.println("------------------------");

        /**
         * 调用静态方法
         */
        Method staticMethod = manClass.getDeclaredMethod("staticMethod");
        staticMethod.setAccessible(true);
        System.out.println("调用静态方法");
        // 如果是静态方法，直接传方法所在的类，或者传 null 也可以
        staticMethod.invoke(Man.class);
        // staticMethod.invoke(null);
        Object returnVal = staticMethod.invoke(Man.class);
        // 方法没有返回值，则返回的是 null
        System.out.println(returnVal);

    }


    /**
     * 获取指定的构造器
     * @throws Exception
     */
    @Test
    public void getConstructor() throws Exception {
        Class<Man> manClass = Man.class;

        // 不需要传入调用的类名，只需要传入调用的构造器形参就行
        Constructor<Man> cons = manClass.getDeclaredConstructor(String.class);
        cons.setAccessible(true);
        Man qi = cons.newInstance("qi");
        System.out.println(qi);
    }
}