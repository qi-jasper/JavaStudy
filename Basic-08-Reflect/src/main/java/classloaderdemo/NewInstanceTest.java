package classloaderdemo;

import demo.Person;
import org.junit.Test;

import java.util.Random;

/**
 * @Description 通过反射，创建运行时类的对象
 * @Author qi
 * @Date 2020/9/28 10:23
 **/

public class NewInstanceTest {

    @Test
    public void instanceTest() throws IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;

        /**
         * 通过 newInstance() 方法创建对应的运行时类对象
         * 创建 Person 类的对象
         * 实际上 newInstance() 方法还是调用了类的构造器创建对象
         * 如果想要这个方法正常运行，运行时类必须提供一个 public 的空参构造器
         */
        Person person = personClass.newInstance();
        System.out.println(person);
    }

    /**
     * 创建一个指定类的对象
     * @param classPath 指定类的全类名
     * @return
     * @throws Exception
     */
    public Object getInstance(String classPath) throws Exception {
        Class<?> clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

    /**
     * 测试一下上面写的方法
     */
    @Test
    public void getTest() throws Exception {
        for (int i = 0; i < 20; i++) {
            int num = new Random().nextInt(3);
            String classPath = null;
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "demo.Person";
                    break;
            }

            Object instance = getInstance(classPath);
            System.out.println(instance);
        }
    }

}