package base.threadcoreknowledge.stopthreads;

/**
 * @Description 最佳实践2：在 catch 子语句中调用 Thread.currentThread().interrupt() 来恢复设置中断状态，以便于在后续的执行中，依然能够检查到刚才发生了中断
 * 回到刚才 RightWayStopThreadInProd 补上中断，让它跳出
 * @Author qi
 * @Date 2021/6/5 15:17
 * @ClassName RightWayStopThreadInProd2
 **/
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}