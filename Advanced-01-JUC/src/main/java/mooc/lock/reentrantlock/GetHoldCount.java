package mooc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 查看锁已经重入了几次
 * @Author qi
 * @Date 2020/7/6 10:32
 * @ClassName GetHoldCount
 **/

public class GetHoldCount {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 打印锁已经被拿到了几次
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
