package getclass;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description 获取运行时类的父类相关结构
 * @Author qi
 * @Date 2020/9/29 11:39
 **/

public class GetFatherClassTest {

    /**
     * 获取运行时类的父类
     */
    @Test
    public void getFatherClass() {
        Class<Man> manClass = Man.class;

        Class<? super Man> superclass = manClass.getSuperclass();
        System.out.println("Man 类的父类为：" + superclass);
    }

    /**
     * 获取运行时类带泛型的父类
     */
    @Test
    public void getFatherClass2() {
        Class<Man> manClass = Man.class;

        Type genericSuperclass = manClass.getGenericSuperclass();
        System.out.println("Man 类的带泛型父类为：" + genericSuperclass);
    }

    /**
     * 获取运行时类带泛型的父类的泛型
     */
    @Test
    public void getFatherClass3() {
        Class<Man> manClass = Man.class;

        Type genericSuperclass = manClass.getGenericSuperclass();
        ParameterizedType param = (ParameterizedType) genericSuperclass;

        // 获取泛型类型
        Type[] argus = param.getActualTypeArguments();

        for (Type argu : argus) {
            System.out.println("泛型类型为：" + argu);
            System.out.println("泛型类型名为：" + argu.getTypeName());
        }

    }
}