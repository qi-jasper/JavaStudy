package ks.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @Description 保证可见性测试
 * @Author qi
 * @Date 2020/5/11 21:21
 * @ClassName JMMDemo
 **/

public class VisibilityDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    // main线程
    public static void main(String[] args) {

        // 线程 1 对主内存的变化不知道的
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);

    }
}
