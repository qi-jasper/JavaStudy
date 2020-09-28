package ks.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 不保证原子性测试
 * @Author qi
 * @Date 2020/5/11 21:26
 * @ClassName AtomicityDemo
 **/

public class AtomicityDemo {

//    private static int num = 0;

    // 加了volatile也无法保证原子性
//    private volatile static int num = 0;

    // 使用java.util.concurrent.atomic下的AtomicInteger类实现
    private volatile static AtomicInteger num = new AtomicInteger();


    public static void add() {
//        num++;
        num.getAndIncrement(); // AtomicInteger + 1方法   底层是CAS
    }

    public static void main(String[] args) {

        // 理论上num结果应该为2w
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000 ; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);

    }

}
