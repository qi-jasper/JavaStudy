package ks.future;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/11 12:25
 * @ClassName AsynchronousDemo
 **/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行，成功回调，失败回调
 */

public class AsynchronousDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 发起一个没有返回值的runAsync回调
        CompletableFuture<Void> com = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "runAsync -> void");
        });

        // 阻塞获取执行结果
        System.out.println("1111");
        com.get();


        // 有返回值的supplyAsync回调
        CompletableFuture<Integer> comp = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+ "supplyAsync -> Integer");
            // 这里出现错误
            int i = 10/0;
            return 1024;
        });

        System.out.println(comp.whenComplete((t, u) -> {
            // 正常的返回结果
            System.out.println("t：" + t);
            // 错误的返回结果
            System.out.println("u：" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            // 运行错误的时候返回的结果
            return 233;
        }).get());
    }
}
