package mooc.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 打印字符串
 * @Author qi
 * @Date 2020/7/6 10:16
 * @ClassName LockDemo
 **/
public class LockDemo {

    static class Outputer {
        Lock lock = new ReentrantLock();
        // 打印字符串的方法，一个个字符的打印
        public void output(String name) {
            int len = name.length();
            // 这里的锁用来锁住，每次将字符串打印完毕
            lock.lock();
            try {
                for (int i = 0; i < len ; i++) {
                    System.out.println(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("我在学习");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("没有工作");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new LockDemo().init();
    }
}
