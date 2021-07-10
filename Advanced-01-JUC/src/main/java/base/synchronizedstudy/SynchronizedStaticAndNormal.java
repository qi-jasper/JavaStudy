package base.synchronizedstudy;

/**
 * @Description 同时访问静态的 synchronized 和非静态的 synchronized 方法
 * @Author qi
 * @Date 2021/7/10 11:38
 * @ClassName SynchronizedStaticAndNormal
 **/
public class SynchronizedStaticAndNormal implements Runnable {

    static SynchronizedStaticAndNormal instance = new SynchronizedStaticAndNormal();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("finished");
    }


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public static synchronized void method1() {
        System.out.println("我是第一个加了 static 和 synchronized 的静态同步方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

    public synchronized void method2() {
        System.out.println("我是第二个加了 synchronized 的普通同步方法，没有加 static，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }
}