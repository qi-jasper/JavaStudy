package getclass;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description 获取运行时类属性结构
 * @Author qi
 * @Date 2020/9/28 14:46
 **/

public class GetFieldTest {

    @Test
    public void fieldTest() {
        Class<Man> manClass = Man.class;

        /**
         * getFields():获取运行时类及其所有父类声明权限为 public 的属性
         */
        Field[] fields = manClass.getFields();
        for (Field field : fields) {
            System.out.println("当前运行时类及其父类声明为 public 权限的所有属性：" + field);
        }

        System.out.println("********");

        /**
         * getDeclaredFields():获取当前运行时类中声明的所有权限的属性，不包含父类的属性
         */
        Field[] declaredFields = manClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("当前运行时类中声明的所有权限的属性：" + declaredField);
        }

        System.out.println("========");

        /**
         * 获取属性的权限修饰符、数据类型、变量名等
         */
        for (Field declaredField : declaredFields) {
            /**
             * getModifiers():获取权限修饰符，返回的是数字
             * 在 Modifier 类中定义了各个数字所代表的权限：
             *      DEFAULT：0
             *      PUBLIC：1
             *      PRIVATE：2
             *      PROTECTED：4
             *      STATIC：8
             *      ...
             *      可以通过 Modifier.toString(int mod) 方法，将值放入，返回对应权限名
             */
            int modifiers = declaredField.getModifiers();
            System.out.println("权限数字：" + modifiers);
            System.out.println("数字对应的权限：" + Modifier.toString(modifiers));

            System.out.println("==========");

            /**
             * getType():获取数据类型
             */
            Class<?> type = declaredField.getType();
            System.out.println("获取数据类型：" + type.getName());

            /**
             * getName():获取变量名
             */
            System.out.println("获取变量名：" + declaredField.getName());
        }

    }
}