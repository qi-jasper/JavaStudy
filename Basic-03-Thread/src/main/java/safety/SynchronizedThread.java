package safety;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 15:47
 * @ClassName SynchronizedThread
 **/

/* 实现方式一：同步代码块
 * 解决继承Thread类的线程安全问题
 *  */

class WindowSafety2 extends Thread {
    private static int ticket = 100;
//    private static Object obj = new Object();

    @Override
    public void run() {

        while (true) {
            // 将共享变量和对象设置成static解决线程安全问题，但是这里的synchronized()中不能放this，因为main函数中有三个WindowsSafety2的对象，wsf1，wsf2，wsf3
            // synchronized (obj) {

            // 但是可以写成 synchronized(WindowSafety2.class) 的形式，以当前类作为对象（类也是对象），因为WindowSafety2指挥加载一次
            synchronized(WindowSafety2.class) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": 票号为" + ticket);

                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class SynchronizedThread {

    public static void main(String[] args) {
        WindowSafety2 wsf1 = new WindowSafety2();
        WindowSafety2 wsf2 = new WindowSafety2();
        WindowSafety2 wsf3 = new WindowSafety2();

        wsf1.setName("线程一");
        wsf2.setName("线程二");
        wsf3.setName("线程三");

        wsf1.start();
        wsf2.start();
        wsf3.start();
    }

}
