package create;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 14:21
 * @ClassName CreateByThread
 **/


/*
 * 方式一：继承于Thread类的方式
 * 创建一个继承于Thread类的子类
 * 重写Thread类的run() -->将此线程要执行的操作声明在run()中
 * 通过此对象调用start()
 * */
class MyThread extends Thread {
    // 重写run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

// 打印奇数
class OddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

// 打印偶数
class EvenThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

public class CreateByThread {

    @Test
    public void myThreadTest() {
        MyThread myThread = new MyThread();
        // 通过调用start() 启用当前线程; 调用当前线程的run()
        myThread.start();

        //如果想要再启动一个线程,需要再创建一个对象
        MyThread myThread2 = new MyThread();
        myThread2.start();

        /*
         * 不能直接使用myThread.run()启用线程
         * */
    }

    // 使用匿名内部类测试打印奇偶数
    @Test
    public void innerClass() {
        /*OddThread oddThread = new OddThread();
        oddThread.start();

        EvenThread evenThread = new EvenThread();
        evenThread.start();*/


        // 使用匿名子类实现
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    }
                }
            }
        }.start();
    }

}
