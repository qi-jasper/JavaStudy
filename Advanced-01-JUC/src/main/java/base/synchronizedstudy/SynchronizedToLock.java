package base.synchronizedstudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author qi
 * @Date 2021/7/10 15:28
 * @ClassName SynchronizedToLock
 **/
public class SynchronizedToLock {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedToLock s = new SynchronizedToLock();
        s.method1();
        s.method2();
    }

    public synchronized void method1() {
        System.out.println("This is synchronized method...");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("This is lock method...");
        } finally {
            lock.unlock();
        }
    }
}