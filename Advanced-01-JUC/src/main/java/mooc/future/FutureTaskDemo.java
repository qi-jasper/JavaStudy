package mooc.future;

import java.util.concurrent.*;

/**
 * @Description 演示 FutureTask 用法
 * @Author qi
 * @Date 2021/3/24 15:43
 * @ClassName FutureTaskDemo
 **/
public class FutureTaskDemo {
    public static void main(String[] args) {
        Task task = new Task();
        // 将实现了 Callable 接口的 task 对象作为参数传入，而 futureTask 对象就可以当成 Runnable 使用
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        // 放到线程中执行
        // new Thread(futureTask).start();

        // 使用线程池执行
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(futureTask);

        try {
            System.out.println("计算结果为：" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算从 1 到 100 的和...");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i <= 100 ; i++) {
                sum = sum + i;
            }
            return sum;
        }
    }
}