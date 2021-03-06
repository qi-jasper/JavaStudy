package mooc.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 使用tryLock()来避免死锁
 * @Author qi
 * @Date 2020/7/5 18:34
 * @ClassName TryLock
 **/
public class TryLock implements Runnable {

    // 使用标记位来时两个线程执行不同都逻辑
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLock t1 = new TryLock();
        TryLock t2 = new TryLock();

        t1.flag = 1;
        t1.flag = 0;
        new Thread(t1).start();
        new Thread(t2).start();
    }

    @Override
    public void run() {
        if (flag == 1) {
            try {
                if (lock1.tryLock(300, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("线程1获取到了锁1");
                        Thread.sleep(100);
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("线程1获取到了锁2");
                                System.out.println("线程1成功获取到了2把锁");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("线程1获取锁2失败！");
                        }
                    } finally {
                        lock1.unlock();
                        Thread.sleep(100);
                    }
                } else {
                    System.out.println("线程1获取锁失败！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (flag == 2) {
            try {
                if (lock2.tryLock(300, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("线程2获取到了锁2");
                        Thread.sleep(100);
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("线程2获取到了锁1");
                                System.out.println("线程2成功获取到了2把锁");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("线程2获取锁1失败！");
                        }
                    } finally {
                        lock2.unlock();
                        Thread.sleep(100);
                    }
                } else {
                    System.out.println("线程1获取锁失败！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}