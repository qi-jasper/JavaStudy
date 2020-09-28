package ks.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/5 13:33
 * @ClassName BlockingQueueDemo
 **/

public class BlockingQueueDemo {

    @Test
    public void arrayBlockingQueue() {
        // 设置队列的大小
        ArrayBlockingQueue abq = new ArrayBlockingQueue<>(3);

        System.out.println(abq.add("a"));
        System.out.println(abq.add("b"));
        System.out.println(abq.add("c"));
        // 这里会报异常，因为超过了数组最大值
        System.out.println(abq.add("d"));

    }

    @Test
    public void arrayBlockingQueue2() {
        // 替代使用offer()方法添加
        ArrayBlockingQueue abq = new ArrayBlockingQueue<>(3);
        System.out.println(abq.offer("a"));
        System.out.println(abq.offer("b"));
        System.out.println(abq.offer("c"));
        // 这里只会返回false
        System.out.println(abq.offer("d"));

        // 返回队首元素
        System.out.println(abq.element());
        // 检测队首元素
        System.out.println(abq.peek());

        System.out.println("接下来取出值");

        // 使用poll()方法取出/移除
        System.out.println(abq.poll());
        System.out.println(abq.poll());
        System.out.println(abq.poll());
        // 这里会返回null，因为数组中只有3个元素
        System.out.println(abq.poll());



    }

    @Test
    public void arrayBlockingQueue3() throws InterruptedException {
        ArrayBlockingQueue abq = new ArrayBlockingQueue<>(3);

        /**
         * 等待，阻塞（一直阻塞）
         */
        abq.put("a");
        abq.put("b");
        abq.put("c");
        // 这时队列中没有多余的位置，所以会一直阻塞
        abq.put("d");

        System.out.println(abq.take());
        System.out.println(abq.take());
        System.out.println(abq.take());
        // 没有第四个元素，所以无法取出，会一直等待
        System.out.println(abq.take());
    }

    @Test
    public void arrayBlockingQueue4() throws InterruptedException {
        ArrayBlockingQueue abq = new ArrayBlockingQueue<>(3);

        /**
         * 超时等待
         */

        System.out.println("=======插入元素======");
        abq.offer("a");
        System.out.println("插入a完成");
        abq.offer("b");
        System.out.println("插入b完成");
        abq.offer("c");
        System.out.println("插入c完成");
        // 等待超过2秒就退出
        abq.offer("d", 2, TimeUnit.SECONDS);
        System.out.println("超时！无法插入！没有空位！");
        System.out.println("=======取出队列中的元素======");
        System.out.println(abq.poll());
        System.out.println(abq.poll());
        System.out.println(abq.poll(2, TimeUnit.SECONDS));
        System.out.println("超时！没有该元素！不再取出！");
    }
}
