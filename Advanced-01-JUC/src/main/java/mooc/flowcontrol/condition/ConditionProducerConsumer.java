package mooc.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Condition 实现生产者消费者模式
 * @Author qi
 * @Date 2021/3/7 16:53
 * @ClassName ConditionProducerConsumer
 **/
public class ConditionProducerConsumer {

    /**
     * 队列大小
     */
    private final int queueSize = 10;

    /**
     * 生产者和消费者之间的队列
     */
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    /**
     * 锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 生产者条件，队列不满
     */
    private Condition notFull = lock.newCondition();

    /**
     * 消费者条件，队列为空
     */
    private Condition notEmpty = lock.newCondition();

    /**
     * main 函数
     * @param args
     */
    public static void main(String[] args) {
        ConditionProducerConsumer condi = new ConditionProducerConsumer();
        Producer producer = condi.new Producer();
        Consumer consumer = condi.new Consumer();
        producer.start();
        consumer.start();
    }

    /**
     * 消费者
     */
    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    // 队列为空的时候，等待队列中有消息再消费
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        notEmpty.await();
                    }
                    // 队列中有消息，开始消费，拿出一个
                    queue.poll();
                    notFull.signalAll();
                    System.out.println("从队列里取走了一个数据，队列剩余：" + queue.size() + " 个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 生产者
     */
    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    // 判断队列是否已满，已满则不生产消息
                    while (queue.size() == queueSize) {
                        System.out.println("队列已满，等待队列有空余");
                        notFull.await();
                    }
                    // 队列中有空位，开始生产
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("向队列里插入了一个数据，队列剩余：" + (queue.size() - 1) + " 个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}