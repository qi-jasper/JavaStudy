package mooc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description 自定义一次性门闩：通过 AQS 实现一个简单的线程协作器
 * @Author qi
 * @Date 2021/3/17 10:18
 * @ClassName OneShotLatch
 **/
public class OneShotLatch {
    private static final class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    /**
     * 加锁方法
     */
    public void await() {
        sync.acquireShared(0);
    }

    /**
     * 释放方法
     */
    public void signal() {
        sync.releaseShared(0);
    }


    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 尝试获取 Latch，获取失败就等待");
                oneShotLatch.await();
                System.out.println(Thread.currentThread().getName() + " 释放锁，继续运行");
            }).start();
        }

        Thread.sleep(5000);
        oneShotLatch.signal();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 尝试获取 Latch，获取失败就等待");
            oneShotLatch.await();
            System.out.println(Thread.currentThread().getName() + " 释放锁，继续运行");
        }).start();
    }
}