package mooc.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 演示 Condition 的基本用法
 * @Author qi
 * @Date 2021/3/7 16:44
 * @ClassName ConditionDemo
 **/
public class ConditionDemo {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            System.out.println("条件不满足，开始 await...");
            condition.await();
            System.out.println("条件满足，开始执行后续任务...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();
        try {
            System.out.println("准备工作完成，开始唤醒其他线程...");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo con = new ConditionDemo();
        // 先开一个子线程
        new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    con.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();

        // 此处执行方法 1，不能在子线程之前启动，不然程序会卡在 method1处
        con.method1();
    }
}