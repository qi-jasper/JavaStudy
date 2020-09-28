package ks.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/5 13:57
 * @ClassName SynchronousQueueDemo
 **/

/**
 * 同步队列和其他的BlockingQueue不一样，SynchronousQueue队列中不存储元素，
 * put了一个元素，必须要从里面取出来，否则不能再put进去！
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // 同步队列
        SynchronousQueue<String> sq = new SynchronousQueue<String>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "===>put 1");
                sq.put("1");
                System.out.println(Thread.currentThread().getName() + "===>put 2");
                sq.put("2");
                System.out.println(Thread.currentThread().getName() + "===>put 3");
                sq.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "===>" + "get " + sq.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "===>" + "get " + sq.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "===>" + "get " + sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }

}
