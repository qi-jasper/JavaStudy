package mooc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 用法一：一个线程等待多个线程都执行完毕，再继续自己的工作。案例：工厂中进行质检操作，5个工人检查，所有人都认为通过时，才代表通过
 * @Author qi
 * @Date 2021/3/6 15:20
 * @ClassName CountDownLatchDemo
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6 ; i++) {
            int no = i;
            Runnable runnable = () -> {
                try {
                    // 设置随机休眠来模拟检测工作
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("No." + no + " 完成了检查");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 一个工人检查完成后减少一项
                    countDownLatch.countDown();
                }
            };
            // 提交任务线程
            service.submit(runnable);
        }
        System.out.println("等待 5 个人都检查完...");
        countDownLatch.await();
        System.out.println("所有人都已经完成检查...");
    }
}