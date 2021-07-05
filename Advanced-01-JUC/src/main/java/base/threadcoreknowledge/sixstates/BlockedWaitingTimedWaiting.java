package base.threadcoreknowledge.sixstates;

/**
 * @Description 演示 Blocked、Waiting、TimedWaiting 三种状态
 * @Author qi
 * @Date 2021/7/5 21:22
 * @ClassName BlockedWaitingTimedWaiting
 **/
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        // 希望打印出 TimedWaiting 状态，因为正在执行 Thread.sleep(1000)；但是实际打印出 Runnable 状态
        // 如果在此语句之前执行 Thread.sleep(1000)，thread2 可以打印出 TimedWaiting
        System.out.println(thread1.getState());
        // 打印出 Blocked 状态，因为 thread2 想要拿到锁却拿不到
        System.out.println(thread2.getState());
        Thread.sleep(1000);
        // 希望打印出 Waiting 状态，因为已经执行了 wait() 方法
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}