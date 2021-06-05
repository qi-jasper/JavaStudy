package base.threadcoreknowledge.stopthreads;

/**
 * @Description 如果在执行过程中，每次循环都会调用 sleep 或 wait 等方法...不需要每次都检查是否已中断
 * @Author qi
 * @Date 2021/6/5 14:31
 * @ClassName RightWayStopThreadWithSleepEveryLoop
 **/
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                // 这里不再需要 !Thread.currentThread().isInterrupted() 判断
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是 100 的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}