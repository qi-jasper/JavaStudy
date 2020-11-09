package classloaderdemo;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 使用 ClassLoader 加载配置文件
 * @Author qi
 * @Date 2020/9/27 16:25
 **/

public class LoadPropertiesTest {

    /**
     * 方式一：使用 propertis 加载配置文件
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
     * 方式二：配置文件默认识别为当前 module 的 src 下
     * @throws Exception
     */
    @Test
    public void loadProperties2() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = LoadPropertiesTest.class.getClassLoader();

        // 路径不对会报错，这条语句会报错
        InputStream resourceAsStream1 = classLoader.getResourceAsStream("src\\\\main\\\\resources\\test.properties");
        // 这条正确
        InputStream resourceAsStream = classLoader.getResourceAsStream("test.properties");

        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user:" + user + ", password:" + password);
    }
}