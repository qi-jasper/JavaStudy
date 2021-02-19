package initclient;

/**
 * @Description  `<clinit>()` 方法在多线程下被同步加锁
 * @Author qi
 * @Date 2021/1/28 21:36
 * @ClassName DeadThreadTest
 **/
public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + ": start...");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + ": end...");
        };

        Thread t1 = new Thread(r, "thread-1");
        Thread t2 = new Thread(r, "thread-2");

        t1.start();
        t2.start();
    }

}

class DeadThread {
    static {
        if (true) {
            System.out.println(Thread.currentThread().getName() + ": Initial this class...");
            while (true) {

            }
        }
    }
}