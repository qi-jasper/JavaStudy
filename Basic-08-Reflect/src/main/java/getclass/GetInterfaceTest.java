package getclass;

import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 * @Description 获取运行时类接口、所在包、注解等
 * @Author qi
 * @Date 2020/9/29 13:48
 **/

public class GetInterfaceTest {

    @Test
    public void getInterface() {
        Class<Man> manClass = Man.class;

        /**
         * 获取运行时类的接口
         */
        Class<?>[] interfaces = manClass.getInterfaces();

        for (Class inf : interfaces) {
            System.out.println("运行时类的接口为：" + inf);
        }

        /**
         * 获取运行时类父类的接口
         */
        Class<? super Man> superclass = manClass.getSuperclass();
        Class<?>[] supInterfaces = superclass.getInterfaces();
        for (Class supInf : supInterfaces) {
            System.out.println("运行时类的父类的接口为：" + supInf);
        }

        /**
         * 获取运行时类所在的包
         */
        Package manPackage = manClass.getPackage();
        System.out.println("运行时类所在的包为：" + manPackage);

        /**
         * 获取运行时类声明的注解
         */
        Annotation[] annotations = manClass.getAnnotations();
        for (Annotation anno : annotations) {
            System.out.println("运行时类标注的注解有：" + anno);
        }
    }
}