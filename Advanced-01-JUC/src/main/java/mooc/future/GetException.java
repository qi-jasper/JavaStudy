package mooc.future;

import java.util.concurrent.*;

/**
 * @Description 演示 get() 方法过程中抛出异常， for 循环是为了演示抛出异常的时机：并不是一产生异常就抛出，而是直到执行 get() 时才抛出
 * @Author qi
 * @Date 2021/3/24 11:56
 * @ClassName GetException
 **/
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);

        Callable<Integer> callable = () -> {
            throw new IllegalArgumentException("Callable 抛出异常");
        };

        Future<Integer> future = service.submit(callable);

        try {
            for (int i = 0; i < 5 ; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            // 仍然打印 true
            System.out.println(future.isDone());
            // 调用 get() 方法
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException 异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            // 实际会打印这个异常
            System.out.println("ExecutionException 异常");
        }

        service.shutdown();

    }
}