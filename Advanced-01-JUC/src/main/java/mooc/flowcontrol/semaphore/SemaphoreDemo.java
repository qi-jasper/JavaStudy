package mooc.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description 演示 Semaphore 的基本用法
 * @Author qi
 * @Date 2021/3/7 15:45
 * @ClassName SemaphoreDemo
 **/
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100 ; i++) {
            service.submit(new Task());
        }
        // 关闭线程池
        service.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                // 获取许可证，一次性拿到 3 个许可证
                semaphore.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 拿到了许可证...");
            try {
                // 模拟线程正在执行的任务
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 释放了许可证...");
            // 释放许可证，一次性也要释放 3 个许可证
            semaphore.release(3);
        }
    }
}