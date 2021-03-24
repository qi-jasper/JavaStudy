package mooc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description 演示一个 Future 的使用方法，Lambda 方式
 * @Author qi
 * @Date 2021/3/24 10:56
 * @ClassName OneFuture
 **/
public class OneFutureLambda {
    public static void main(String[] args) {
        // 新建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // Lambda 表达式写法
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };
        // 提交任务
        Future<Integer> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        service.shutdown();
    }
}