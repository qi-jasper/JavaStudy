package getclass;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Description 获取运行时类的方法结构
 * @Author qi
 * @Date 2020/9/28 15:10
 **/

public class GetMethodTest {

    /**
     * 获取方法
     */
    @Test
    public void methodTest() {
        Class<Man> manClass = Man.class;

        /**
         * getMethods():获取当前运行时类及其所有父类声明权限为 public 的方法
         */
        Method[] methods = manClass.getMethods();
        for (Method method : methods) {
            System.out.println("运行时类及其父类声明的所哟 public 方法有：" + method);
        }

        System.out.println("========");

        /**
         * getDeclaredMethods():获取当前运行时类中声明的所有权限的方法
         */
        Method[] declaredMethods = manClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("运行时类声明的所有权限的方法有：" + declaredMethod);
        }
    }

    /**
     * 获取方法的内部结构
     * 权限修饰符、返回值类型、方法名及其参数...
     */
    @Test
    public void methodStructureTest() {
        Class<Man> manClass = Man.class;

        /**
         * 获取方法声明的注解
         */
        Method[] declaredMethods = manClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            Annotation[] annos = m.getAnnotations();
            for (Annotation anno : annos) {
                System.out.println("所有方法上的注解：" + anno);
            }
        }

        /**
         * 获取权限修饰符及其返回值类型
         */
        for (Method m : declaredMethods) {
            System.out.print("得到每个方法的权限修饰符：" + Modifier.toString(m.getModifiers()) + "\t");
            System.out.println();

            // 获得返回值类型
            System.out.println("方法的返回值类型" + m.getReturnType().getName() + "\t");

            // 获得方法名
            System.out.print("方法名" + m.getName());
            System.out.print("(");
            // 获得形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            // 有形参的情况
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
                }
            }
        }

    }
}