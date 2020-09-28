package mooc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author qi
 * @Date 2020/7/5 21:16
 * @ClassName LockInterruptibly
 **/

public class LockInterruptibly implements Runnable {

    public static void main(String[] args) {
        LockInterruptibly lock = new LockInterruptibly();
        Thread t0 = new Thread(lock);
        Thread t1 = new Thread(lock);
        t0.start();
        t1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t0.interrupt();
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 尝试获取锁...");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到了锁");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 获取锁期间被中断了");
        }
    }
}
