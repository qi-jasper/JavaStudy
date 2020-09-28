package classloaderdemo;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/9/27 16:15
 **/

public class ClassLoaderTest {

    @Test
    public void classLoaderTest() {

        /**
         * 获取本类的类加载器（系统类加载器：SystemClassLoader）
         * 对于自定义类，使用系统类加载器进行加载：classLoader.loadClass("全路径类名");
         */
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        // 获取拓展类加载器（扩展类加载器：ExtensionClassLoader）
        ClassLoader extensionClassLoader = classLoader.getParent();
        System.out.println(extensionClassLoader);

        // 尝试获取引导类加载器
        ClassLoader parent = extensionClassLoader.getParent();
        // 引导类加载器主要负责加载 Java 核心类库，无法加载自定义类的，获取失败，返回 null
        System.out.println(parent);

    }

}