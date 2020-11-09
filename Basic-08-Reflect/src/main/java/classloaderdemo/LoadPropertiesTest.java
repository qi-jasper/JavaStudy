package classloaderdemo;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 使用 ClassLoader 加载配置文件
 * @Author qi
 * @Date 2020/9/27 16:25
 **/

public class LoadPropertiesTest {

    /**
     * 方式一：使用 Propertis + FileInputStream 加载配置文件
     * @throws Exception
     */
    @Test
    public void loadProperties() throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\test.properties");

        properties.load(fileInputStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user:" + user + ", password:" + password);
    }

    /**
     * 方式二：使用 Class.getClass().getResource("/")
     *        - 如果以 / 开头，默认从根路径开始搜索资源，即 `项目名称/target/classes`
     *        - 如果不以 / 开头，默认从当前路径开始搜索资源，即从此类所在的包下取资源
     *        - this.getClass().getResourceAsStream(): 直接将要读取的文件转化为流的形式了，相当于做了 `FileInputStream()`
     *        - `this.getClass().getResource("/test.properties").getPath()` 和 `this.getClass().getResource("/test.properties").getFile()` 作用一致
     */
    @Test
    public void loadProperties2() {
        String path0 = LoadPropertiesTest.class.getClass().getResource("/").getPath();
        // /C:/Users/qi/Documents/Code/Git/MyRepo/Java/JavaStudy/Basic-08-Reflect/target/classes/
        System.out.println(path0);

        String path = LoadPropertiesTest.class.getClass().getResource("/test.properties").getPath();
        // /C:/Users/qi/Documents/Code/Git/MyRepo/Java/JavaStudy/Basic-08-Reflect/target/classes/test.properties
        System.out.println(path);

        String path1 = this.getClass().getResource("/test.properties").getPath();
        // String path1 = this.getClass().getResource("/test.properties").getPath();
        // /C:/Users/qi/Documents/Code/Git/MyRepo/Java/JavaStudy/Basic-08-Reflect/target/classes/test.properties
        System.out.println(path1);

        String path2 = this.getClass().getResource("LoadPropertiesTest.class").getPath();
        // /C:/Users/qi/Documents/Code/Git/MyRepo/Java/JavaStudy/Basic-08-Reflect/target/classes/classloaderdemo/LoadPropertiesTest.class
        System.out.println(path2);

    }

    /**
     * 方式三：Class.getClassLoader().getResource(): 的资源获取不能以 / 开头，统一从根路径开始搜索资源
     * 配置文件默认识别为当前 module 的 src 下
     * Class.getClassLoader().getResourceAsStream(): 直接将要读取的文件转化为流的形式了，相当于做了 FileInputStream()
     *
     * @throws Exception
     */
    @Test
    public void loadProperties3() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = LoadPropertiesTest.class.getClassLoader();

        // 路径不对会报错，这条语句会报错
        InputStream resourceAsStream1 = classLoader.getResourceAsStream("src\\main\\resources\\test.properties");
        // 这条正确
        InputStream resourceAsStream = classLoader.getResourceAsStream("test.properties");

        // /C:/Users/qi/Documents/Code/Git/MyRepo/Java/JavaStudy/Basic-08-Reflect/target/classes/test.properties
        System.out.println(classLoader.getResource("test.properties").getPath());

        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user:" + user + ", password:" + password);
    }
}