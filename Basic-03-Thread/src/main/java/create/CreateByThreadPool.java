package create;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 14:37
 * @ClassName CreateByThreadPool
 **/

/*创建线程方式四：使用线程池
 * 提前创建好多个线程，放入线程池，使用时直接获取，使用问放回线程池。可以避免频繁创建销毁、实现重复利用
 * 优点；
 *   1. 提高响应速度（减少了创建新线程的时间）
 *   2. 降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
 *   3. 便于线程的管理
 *       corePoolSize：核心池的大小
 *       maximumPoolSize：最大线程数
 *       keepAliveTime：线程没有任务时最多保持多长时间后终止
 *
 * ExecutorService：真正的线程池接口，常见子类ThreadPoolExecutor
 *   void execute(Runnable command)：执行任务，没有返回值，一般用来执行Runnable
 *   <T> Future<T> submit(Callable<T> task)：执行任务，有返回值，一般用来执行Callable
 *   void shutdown()：关闭连接池
 *
 * Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *   Executors.newCachedThreadPool()：创建一个可以根据需要创建新线程的线程池
 *   Executors.newFixedThreadPool(n)：创建一个可重用固定线程数的线程池
 *   Executors.newSingleThreadExecutor()：创建一个只有一个线程的线程池
 *   Executors.newScheduledThreadPool(n)：创建一个线程池，它可以安排在给定延迟后运行命令或者定期执行。
 *
 */

class NumberThread implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<=100; i++) {
            if(i%2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class CreateByThreadPool {

    @Test
    public void threadPoolTest() {
        // 1. 指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        // 做一些设置，设置线程池的属性
        System.out.println(service.getClass()); // 获取实现类
        /*service1.setCorePoolSize(15);
        service1.setKeepAliveTime();*/

        // 2. 执行指定的线程的操作，execute()中说明线程要执行的任务
        service.execute(new NumberThread());  // 适合适用与Runnable
//        service.submit(Callable callable);   // 适合适用于Callable

        // 3. 关闭线程池
        service.shutdown();
    }
}
