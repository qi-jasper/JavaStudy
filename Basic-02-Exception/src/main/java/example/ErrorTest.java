package example;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 7:46
 * @ClassName ErrorTest
 **/

public class ErrorTest {

    @Test
    public void ErrorExample() {
        // 堆溢出：java.lang.OutOfMemoryError
        Integer[] arr = new Integer[1024*1024*1024];
    }
}
