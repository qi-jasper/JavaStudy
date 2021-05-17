package heap;

/**
 * @Description
 * @Author qi
 * @Date 2021/5/15 21:15
 * @ClassName SynchronizedTest
 **/
public class SynchronizedTest {
    public void f() {
        Object hell = new Object();
        synchronized(hell) {
            System.out.println(hell);
        }
    }
}