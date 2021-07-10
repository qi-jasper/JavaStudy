package base.synchronizedstudy;

/**
 * @Description 消失的请求
 * @Author qi
 * @Date 2021/7/6 21:42
 * @ClassName DisappearRequest
 **/
public class DisappearRequest implements Runnable {

    static DisappearRequest INSTANCE = new DisappearRequest();

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(INSTANCE);
        Thread thread2 = new Thread(INSTANCE);
        thread1.start();
        thread2.start();

        // join() 方法：线程一执行完后才会继续后面的操作
        thread1.join();
        thread2.join();

        System.out.println(count);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10000; i++) {
            count++;
        }
    }
}