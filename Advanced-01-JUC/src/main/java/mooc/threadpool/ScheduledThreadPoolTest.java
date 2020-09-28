package mooc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description newScheduledThreadPool
 * @Author qi
 * @Date 2020/6/15 15:29
 * @ClassName ScheduledThreadPoolTest
 **/

public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

        // delay:延迟时间，设置延迟时间为5s，即每5s执行一次线程
        threadPool.schedule(new Task(), 5, TimeUnit.SECONDS);

        // initialDelay:开始是1s，period:每隔3s执行一次任务
        threadPool.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}