package mooc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description 演示一个 Future 的使用方法
 * @Author qi
 * @Date 2021/3/24 10:56
 * @ClassName OneFuture
 **/
public class OneFuture {
    public static void main(String[] args) {
        // 新建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 提交任务
        Future<Integer> future = service.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}