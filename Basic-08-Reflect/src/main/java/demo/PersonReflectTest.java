package demo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Description
 * @Author qi
 * @Date 2020/9/27 11:45
 **/

public class PersonReflectTest {

    /**
     * 正常使用
     */
    @Test
    public void normalTest() {
        Person person = new Person();
        person.setName("qi");
        person.show();
    }

    /**
     * 使用反射
     */
    @Test
    public void reflectTest() throws Exception {
        Class personClass = Person.class;

        // 通过反射获取构造器
        Constructor cons = personClass.getConstructor(String.class, int.class);

        // 通过反射实例化一个对象
        Object person = cons.newInstance("qi", 18);
        // 看看调的谁
        System.out.println(person.toString());

        // 通过反射获取 name 属性
        Field name = personClass.getDeclaredField("name");
        // 给上面通过反射获取到的 person 对象将 name 属性设置为 haha
        name.set(person, "haha");

        // 通过反射调用空参的 show() 方法，可以传入方法的参数来指定调的什么方法
        Method show = personClass.getDeclaredMethod("show");
        // 这样就调用了 show() 方法
        show.invoke(person);
    }

    /**
     * 调用私有属性/方法
     */
    @Test
    public void privateTest() throws Exception {
        Class personClass = Person.class;

        // 调用私有构造器
        Constructor dec = personClass.getDeclaredConstructor(String.class);
        dec.setAccessible(true);
        Object obj = dec.newInstance("Jade");
        System.out.println(obj);

        // 调用私有的属性
        Field number = personClass.getDeclaredField("number");
        number.setAccessible(true);
        // 通过 obj 对象，将 number 设置为 777
        number.set(obj, 777);
        System.out.println(obj);

        // 调用私有方法，因为私有方法 print() 有个 String 类型的参数
        Method print = personClass.getDeclaredMethod("print", String.class);
        print.setAccessible(true);
        // 通过 obj 对象调用
        print.invoke(obj, "hahaha");
        // 私有方法 print() 有个返回值，这个方法的返回值可以通过以下方式获得
        // String haha = (String) print.invoke(obj, "haha");
        // System.out.println(haha);

    }
}