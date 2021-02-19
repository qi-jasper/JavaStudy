package classloader;

/**
 * @Description
 * @Author qi
 * @Date 2021/1/28 22:03
 * @ClassName ClassLoaderTest
 **/
public class ClassLoaderTest {

    public static void main(String[] args) {

        // 获取系统类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取其上层的：扩展类加载器：sun.misc.Launcher$ExtClassLoader@677327b6
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        // 试图获取引导类加载器：获取不到，返回 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        // 获取自定义加载器：sun.misc.Launcher$AppClassLoader@18b4aac2，与上面的系统类加载器完全一致，内存地址也一致
        // 由此可见：对于用户自定义类来说，默认使用系统类加载器进行加载
        ClassLoader userClassLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(userClassLoader);

        // 获取 String 类型的加载器：获取不到，返回 null
        // 可见，String 类是使用引导类加载器进行加载的 --> Java 的核心类库都是使用引导类加载器进行加载的
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader);
    }

}