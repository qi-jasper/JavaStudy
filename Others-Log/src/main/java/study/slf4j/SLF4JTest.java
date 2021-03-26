package study.slf4j;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author qi
 * @Date 2021/3/25 16:57
 * @ClassName SLF4JTest
 **/
public class SLF4JTest {

    public static final Logger LOGGER = LoggerFactory.getLogger("SLF4JTest");

    @Test
    public void quickStartTest() {
        // 日志的输出
        LOGGER.error("ERROR");
        LOGGER.warn("WARNNING");
        // 默认级别
        LOGGER.info("INFO");
        LOGGER.debug("DEBUG");
        LOGGER.trace("TRANCE");

        // 使用占位符输出日志信息
        String name = "qi";
        Integer age = 24;
        LOGGER.info("用户: {}, {}", name, age);

        // 将系统的异常信息输出
        try {
            int i = 1/0;
        } catch (Exception e) {
            LOGGER.error("出现异常：", e);
        }

    }

}