package ks.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description Executors：工具类，3大方法
 * @Author qi
 * @Date 2020/5/5 14:21
 * @ClassName ExecutorsDemo
 **/

public class ExecutorsDemo {
    public static void main(String[] args) {
        // 单个线程
        ExecutorService threadpool = Executors.newSingleThreadExecutor();
        // 创建一个固定的线程池的大小
//        ExecutorService threadpool = Executors.newFixedThreadPool(5);
        // 创建一个可伸缩的线程池，遇强则强，遇弱则弱
//        ExecutorService threadpool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadpool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadpool.shutdown();
        }
    }
}
