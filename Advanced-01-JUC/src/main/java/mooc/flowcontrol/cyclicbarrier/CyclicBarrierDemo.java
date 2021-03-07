package mooc.flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description 演示 CyclicBarrier 基本使用
 * @Author qi
 * @Date 2021/3/7 17:25
 * @ClassName CyclicBarrierDemo
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyc = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人都到了");
            }
        });
        for (int i = 0; i < 5 ; i++) {
            new Thread(new CycTask(cyc, 5)).start();
        }
    }

    static class CycTask implements Runnable {

        private int id;
        private CyclicBarrier cyclicBarrier;

        public CycTask(CyclicBarrier cyclicBarrier, int id) {
            this.cyclicBarrier = cyclicBarrier;
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "现在前往集合地点");
            try {
                Thread.sleep((long) (Math.random()*10000));
                System.out.println("线程" + id + "到了集合地点，开始等待其他人到达");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}