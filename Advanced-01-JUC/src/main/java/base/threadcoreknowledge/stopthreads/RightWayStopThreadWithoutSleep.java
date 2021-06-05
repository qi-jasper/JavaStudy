package base.threadcoreknowledge.stopthreads;

/**
 * @Description run() 方法内没有 sleep 或 wait 方法时，停止线程
 * @Author qi
 * @Date 2021/6/5 13:15
 * @ClassName RightWayStopThreadWithoutSleep
 **/
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + ": 是 10000 的倍数");
            }
            num++;
        }
        System.out.println("任务运行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}