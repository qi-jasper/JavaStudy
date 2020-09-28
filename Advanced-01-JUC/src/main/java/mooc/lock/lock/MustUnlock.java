package mooc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Lock不会像synchronized一样，异常的时候自动释放锁，所以要在finally中释放锁
 * @Author qi
 * @Date 2020/7/5 18:21
 * @ClassName MustUnlock
 **/

public class MustUnlock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            // 获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + " start task");
        } finally {
            lock.unlock();
        }
    }
}
