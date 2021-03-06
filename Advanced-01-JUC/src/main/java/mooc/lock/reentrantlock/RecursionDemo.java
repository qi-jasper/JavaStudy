package mooc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 递归调用都情况
 * @Author qi
 * @Date 2020/7/6 10:36
 * @ClassName RecursionDemo
 **/
public class RecursionDemo {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println("这把锁被调用了" + lock.getHoldCount() + "次");
                // 递归调用
                accessResource();
                System.out.println("这把锁被调用了" + lock.getHoldCount() + "次");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
