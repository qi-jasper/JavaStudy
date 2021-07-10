package base.synchronizedstudy;

/**
 * @Description 同时访问一个类的不同的普通同步方法
 * @Author qi
 * @Date 2021/7/10 11:24
 * @ClassName SynchronizedDifferentMethod
 **/
public class SynchronizedDifferentMethod implements Runnable {

    static SynchronizedDifferentMethod instance = new SynchronizedDifferentMethod();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("finished");
    }

    public synchronized void method1() {
        System.out.println("我是第一个加了 synchronized 的普通同步方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

    public synchronized void method2() {
        System.out.println("我是第二个加了 synchronized 的普通同步方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }
}