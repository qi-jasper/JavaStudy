package base.synchronizedstudy;

/**
 * @Description 对象锁示例一：代码块锁
 * @Author qi
 * @Date 2021/7/7 22:35
 * @ClassName SynchronizedObjectCodeBlock
 **/
public class SynchronizedObjectCodeBlock implements Runnable {

    static SynchronizedObjectCodeBlock instance = new SynchronizedObjectCodeBlock();

    /** 声明一个对象，用作锁对象 */
    Object lock1 = new Object();
    Object lock2 = new Object();

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
        // 使用声明的 Object 作为锁
        synchronized (lock1) {
            System.out.println("我是 lock1，我叫：" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " lock1 部分运行结束");
        }

        synchronized (lock2) {
            System.out.println("我是 lock2，我叫：" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " lock2 部分运行结束");
        }
    }
}