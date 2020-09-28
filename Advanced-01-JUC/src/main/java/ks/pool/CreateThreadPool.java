package ks.pool;

import java.util.concurrent.*;

/**
 * @Description 手动创建线程池，自行设置参数
 * @Author qi
 * @Date 2020/5/5 14:40
 * @ClassName CreateThreadPool
 **/

public class CreateThreadPool {
    public static void main(String[] args) {
        // 自定义线程池
        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                /**
                 * 如何确定线程池最大容量
                 * 1.CPU密集型：CPU是几核，就定义为几，可以保证CPU的效率最高，
                 *      通过Runtime.getRuntime().availableProcessors()获取电脑CPU的核心数，不能写死！！！
                 * 2.IO密集型：判断你的程序中十分耗费IO的线程，一般大于该值的2倍。
                 */
                5,  // Runtime.getRuntime().availableProcessors()
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                // 默认的线程创建工厂，一般不会改变
                Executors.defaultThreadFactory(),

                /**
                 * 四大拒绝策略
                 * new ThreadPoolExecutor.AbortPolicy()：线程池满了，还有使用需求，则不处理该线程，抛出异常
                 * new ThreadPoolExecutor.CallerRunsPolicy()：哪来的去哪里，这里是在main函数中，所以给main线程处理
                 * new ThreadPoolExecutor.DiscardOldestPolicy()：队列满了，就会丢掉任务，且不会抛出异常
                 * new ThreadPoolExecutor.DiscardOldestPolicy()：队列满了，尝试和最早的竞争
                 */
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            /**
             * 最大承载：LinkedBlockingDeque + maximumPoolSize
             * 按照设置的参数来，如上设置的为5，LinkedBlockingDeque中设置的为3，所以最大承载为8
             * 使用AbortPolicy()仍然会抛出java.util.concurrent.RejectedExecutionException异常
             */
            for (int i = 0; i < 8; i++) {
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
