package base.synchronizedstudy;

/**
 * @Description 对象锁示例二：方法锁形式
 * @Author qi
 * @Date 2021/7/8 22:02
 * @ClassName SynchronizedObjectMethod
 **/
public class SynchronizedObjectMethod implements Runnable {

    static SynchronizedObjectMethod instance = new SynchronizedObjectMethod();

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
        method();
    }

    public synchronized void method() {
        System.out.println("我是对象锁的方法修饰符的形式，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 运行结束");
    }
}