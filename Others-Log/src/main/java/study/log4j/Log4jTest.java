package study.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @Description
 * @Author qi
 * @Date 2021/3/23 9:46
 * @ClassName Log4jTest
 **/
public class Log4jTest {

    @Test
    public void quickStartTest() {

        // 开启 Log4j 内置日志记录
//        LogLog.setInternalDebugging(true);

        // 因为没有自定义文件，在入门案例中暂不使用配置文件，所以这里初始化配置信息
//        BasicConfigurator.configure();
        // 创建日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);
        // 日志记录输出
        logger.info("Quick Start");

        // 日志级别
        // 严重错误：表示严重错误，一般会造成系统崩溃
        logger.fatal("fatal");

        // 错误信息，不会影响系统运行
        logger.error("error");
        // 警告信息，可能会发生的问题
        logger.warn("warning");
        // 运行信息，数据库连接，网络连接，IO 操作等
        logger.info("info");
        // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等。这是 log4j 默认级别
        logger.debug("debug");

        // 追踪信息，记录程序所有的流程信息。这是 log4j 最低级别
        logger.trace("trance");
    }

    @Test
    public void definedLoggerTest() {
        // 创建日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);
        logger.info("definedLogger");
    }

}