package base.synchronizedstudy;

/**
 * @Description 方法抛出异常后，会释放锁。展示不抛出异常前和抛出异常后的对比：一旦抛出了异常，第二个线程会立刻进入同步方法，意味着锁已经释放
 * @Author qi
 * @Date 2021/7/10 11:43
 * @ClassName SynchronizedException
 **/
public class SynchronizedException implements Runnable {

    static SynchronizedException instance = new SynchronizedException();

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
        System.out.println("我是第一个会抛出异常的同步方法，我叫：" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();

        // 这里就不能打印这句话了，因为抛出了异常，不会执行到这里
        // System.out.println(Thread.currentThread().getName() + " 运行结束");
    }

    public synchronized void method2() {
        System.out.println("我是第二个同步方法，我叫：" + Thread.currentThread().getName());

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