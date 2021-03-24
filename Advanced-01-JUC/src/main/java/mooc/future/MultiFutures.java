package mooc.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description 演示批量提交任务时，用 List 来批量接收结果
 * @Author qi
 * @Date 2021/3/24 11:36
 * @ClassName MultiFutures
 **/
public class MultiFutures {

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 结果 List
        ArrayList<Future> futures = new ArrayList<>();
        // 模拟任务
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        };

        // 放入
        for (int i = 0; i < 20 ; i++) {
            Future<Integer> future = service.submit(callable);
            futures.add(future);
        }

        // 假设有 5 秒等待，会发现结果是一次出现所有结果（实验失败）
         Thread.sleep(5000);

        // 取出
        for (int i = 0; i < 20 ; i++) {
            try {
                System.out.println(futures.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        service.shutdown();
    }
}