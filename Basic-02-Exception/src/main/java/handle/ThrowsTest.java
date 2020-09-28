package handle;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 11:03
 * @ClassName ThrowsTest
 **/

/*
    方式2. throws + 异常类型
    try-catch-finally 是将异常处理掉，而throws只是将异常抛出，并没有真正将异常处理掉
 */
public class ThrowsTest {

    @Test
    public void throwsTest() throws IOException {
        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream(file);

        int data = fis.read();
        while (data != -1) {
            System.out.println(data);
            data = fis.read();
        }

        fis.close();
    }
}
