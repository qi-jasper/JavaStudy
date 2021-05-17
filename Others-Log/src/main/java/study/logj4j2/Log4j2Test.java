package study.logj4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2021/4/24 22:41
 * @ClassName Log4j2Test
 **/
public class Log4j2Test {

    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    /**
     * 快速入门
     */
    @Test
    public void quickStart() {
        // 日志输出，级别由高到低
        LOGGER.fatal("fatal");
        // 默认级别
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}