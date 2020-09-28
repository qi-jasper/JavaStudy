package ks.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description 自旋锁
 * @Author qi
 * @Date 2020/5/12 16:16
 * @ClassName SpinLockDemo
 **/

public class SpinLockDemo {

    AtomicReference<Thread> ato = new AtomicReference<>();
    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> mylock");
        // 自旋锁
        while (!ato.compareAndSet(null, thread)){
        }
    }

    // 解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnlock");
        ato.compareAndSet(thread, null);
    }

}


class LockTest {
    public static void main(String[] args) throws Exception {
        /*ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.ks.lock();
        reentrantLock.unlock();*/

        // 自己写的锁，底层使用的自旋锁CAS
        SpinLockDemo lock = new SpinLockDemo();

        new Thread(()-> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        },"T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        },"T2").start();

    }
}
