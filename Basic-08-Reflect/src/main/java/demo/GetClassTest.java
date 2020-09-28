package demo;

import org.junit.Test;

/**
 * @Description 获取 Class 实例的 4 种方式
 * @Author qi
 * @Date 2020/9/27 15:03
 **/

public class GetClassTest {

    /**
     * 方式一：调用运行时属性：.class
     */
    @Test
    public void getClass1() {
        Class<Person> personClass = Person.class;
        System.out.println(personClass);
    }

    /**
     * 方式二：通过运行时类的对象，调用 getClass()
     */
    @Test
    public void getClass2() {
        Person person = new Person();
        Class personClass = person.getClass();
        System.out.println(personClass);
    }

    /**
     * 方式三：调用 Class 的静态方法：forName(String classPath)
     * 需要传入全路径类名
     */
    @Test
    public void getClass3() throws ClassNotFoundException {
        Class personClass = Class.forName("demo.Person");
        System.out.println(personClass);
    }

    /**
     * 方式四：使用类的加载器 ClassLoader
     */
    @Test
    public void getClass4() throws ClassNotFoundException {
        // 获取本类的 ClassLoader
        ClassLoader classLoader = GetClassTest.class.getClassLoader();
        // 通过 ClassLoader 显示地加载一个类
        Class personClass = classLoader.loadClass("demo.Person");
        System.out.println(personClass);
    }
}