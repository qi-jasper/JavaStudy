package base.threadcoreknowledge.stopthreads;

/**
 * @Description 最佳实践：catch 了 InterruptedExcetion 之后的优先选择：在方法签名中抛出异常 那么在 run() 就会强制 try/catch
 * @Author qi
 * @Date 2021/6/5 15:10
 * @ClassName RightWayStopThreadInProd
 **/
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // 保存日志、停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    /**
     * 直接 throws 异常
     * @throws InterruptedException
     */
    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}