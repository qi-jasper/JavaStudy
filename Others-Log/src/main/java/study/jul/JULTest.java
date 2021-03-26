package study.jul;



import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * @Description
 * @Author qi
 * @Date 2021/3/21 20:21
 * @ClassName JULTest
 **/
public class JULTest {

    /**
     * 入门 Demo
     */
    @Test
    public void quickTest() {
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("JULTest");
        // 2.日志记录输出
        logger.info("JUL Test");
        
        // 通用方法进行日志记录
        logger.log(Level.INFO, "info message");

        // 通过占位符的方式输出变量值
        String name = "qi";
        Integer age = 23;
        logger.log(Level.INFO, "用户信息：姓名：{0}，年龄：{1}", new Object[]{name, age});
    }

    /**
     * 日志等级
     */
    @Test
    public void logLevelTest() {
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("logLevelTest");
        // 2.日志记录输出
        logger.severe("SERVERE");
        logger.warning("WARNING LEVEL");
        // JUL 默认日志级别是 INFO，其下面的日志信息不会被输出
        logger.info("INFO LEVEL");
        logger.config("CONFIG LEVEL");
        // 一般 debug 时使用该级别
        logger.fine("FINE LEVE");
    }

    /**
     * 自定义日志等级
     */
    @Test
    public void definedLogLevel() {
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("definedLogLevel");

        // 2.关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 3.自定义配置日志级别
        // 3-1.创建 ConsolHandler，这是控制台输出处理，还可以创建 FileHandler 文件输出处理
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // FileHandler fileHandler = new FileHandler("/logs/jul.log");

        // 3-2.创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 4.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        // fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // logger.addHandler(fileHandler);

        // 5.配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger.severe("SERVERE");
        logger.warning("WARNING LEVEL");
        logger.info("INFO LEVEL");
        logger.config("CONFIG LEVEL");
        logger.fine("FINE LEVE");
    }

    /**
     * Logger 对象父子关系
     */
    @Test
    public void loggerRelationship() {
        // study 下的 jul 包
        Logger logger1 = Logger.getLogger("study.jul");
        // study 包
        Logger logger2 = Logger.getLogger("study");

        // logger2 是否是 logger1 的父对象:true
        System.out.println(logger1.getParent() == logger2);
        // logger2 的父对象：java.util.logging.LogManager$RootLogger, 它是所有日志记录器的顶级父元素，其名称为：(空)
        System.out.println("logger2 的父对象：" + logger2.getParent() + ", 它是所有日志记录器的顶级父元素，其名称为：" + logger2.getParent().getName());
    }

    /**
     * 加载自定义的日志配置文件
     */
    @Test
    public void definedLogPropertiesTest() throws IOException {
        // 通过类加载器读取配置文件
        InputStream ins = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        // 创建 LogManager
        LogManager logManager = LogManager.getLogManager();
        // 通过 LogManager 加载配置文件
        logManager.readConfiguration(ins);

        // 创建日志记录器
        Logger logger = Logger.getLogger("definedLogPropertiesTest");

        logger.info("INFO LEVEL");
    }
}