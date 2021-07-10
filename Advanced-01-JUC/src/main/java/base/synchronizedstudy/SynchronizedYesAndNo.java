package base.synchronizedstudy;

/**
 * @Description 同时访问同步方法与非同步方法
 * @Author qi
 * @Date 2021/7/10 10:36
 * @ClassName SynchronizedYesAndNo
 **/
public class SynchronizedYesAndNo implements Runnable {

    static SynchronizedYesAndNo instance = new SynchronizedYesAndNo();

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

    public synchronized void method1() {
        System.out.println("我是被 synchronized 修饰的方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

    public void method2() {
        System.out.println("我是没有被 synchronized 修饰的方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }
}