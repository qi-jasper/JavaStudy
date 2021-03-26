package study.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description logback
 * @Author qi
 * @Date 2021/3/26 10:57
 * @ClassName LogbackTest
 **/
public class LogbackTest {

    public static final Logger LOGGER = LoggerFactory.getLogger("LogbackTest");

    @Test
    public void quickStartTest() {
        // 日志的输出
        LOGGER.error("ERROR");
        LOGGER.warn("WARNNING");
        LOGGER.info("INFO");
        // Logback 默认级别是 debug
        LOGGER.debug("DEBUG");
        LOGGER.trace("TRANCE");

        // 使用占位符输出日志信息
        String name = "qi";
        Integer age = 24;
        LOGGER.info("用户: {}, {}", name, age);
    }
}