package getclass;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @Description 获取构造器结构相关
 * @Author qi
 * @Date 2020/9/29 11:33
 **/

public class GetConstructorTest {

    @Test
    public void constructorTest() {
        Class<Man> manClass = Man.class;

        /**
         * getConstructors()：只能获取到当前运行时类中声明为 public 的构造器
         */
        Constructor<?>[] constructors = manClass.getConstructors();
        for (Constructor c : constructors) {
            System.out.println("获取到 Man 类中声明为 public 的构造器：" + c);
        }

        System.out.println("--------------");

        /**
         * getDeclaredConstructors()：获取当前运行时类中声明的所有权限的构造器
         */
        Constructor<?>[] declaredConstructors = manClass.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println("获取 Man 类中声明的所有权限的构造器：" + c);
        }
    }
}