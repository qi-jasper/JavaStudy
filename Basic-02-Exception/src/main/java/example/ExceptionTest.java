package example;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 8:52
 * @ClassName ExceptionTest
 **/

public class ExceptionTest {

    @Test
    public void ExceptionExample() {
        // java.lang.StringIndexOutOfBoundsException
        String str = "abc";
        System.out.println(str.charAt(3));

        // java.lang.NullPointerException
        String str2 = null;
        System.out.println(str2.charAt(0));
    }
}
