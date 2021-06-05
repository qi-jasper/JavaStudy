package base.threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description 用中断来修复刚才的无尽等待问题
 * @Author qi
 * @Date 2021/6/5 19:49
 * @ClassName WrongWayVolatileFixed
 **/
public class WrongWayVolatileFixed {

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed body = new WrongWayVolatileFixed();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");


        producerThread.interrupt();
    }


    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }


        @Override
        public void run() {
            int num = 0;
            try {
                // 检查线程是否被中断
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + " 是 100 的倍数,被放到仓库中了。");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    class Consumer {

        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}