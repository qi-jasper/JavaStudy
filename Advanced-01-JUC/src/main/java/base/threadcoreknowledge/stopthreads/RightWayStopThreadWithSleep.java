package base.threadcoreknowledge.stopthreads;

/**
 * @Description run() 方法中执行了 sleep 的中断线程写法
 * @Author qi
 * @Date 2021/6/5 14:23
 * @ClassName RightWayStopThreadWithSleep
 **/
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是 100 的倍数");
                }
                num++;
            }

            try {
                // 休眠 1000ms
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}