package mooc.conllections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description 案例：有 10 个面试者，一共只有 1 个面试官，大厅里有 3 个座位供面试者等候，每个人的面试时间是 10 秒，模拟所有人的面试场景
 * @Author qi
 * @Date 2021/3/5 21:23
 * @ClassName ArrayBlockingQueueDemo
 **/
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
    }

}

class Interviewer implements  Runnable {
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10 个面试者都来了");
        for (int i = 0; i < 10 ; i++) {
            String candidate = "Candidate-" + i;
            try {
                queue.put(candidate);
                System.out.println(candidate + " 在等候面试");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String msg;
        try {
            while (!(msg = queue.take()).equals("stop")) {
                System.out.println(msg + " 面试结束了");

            }
            System.out.println("所有面试者都面试完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}