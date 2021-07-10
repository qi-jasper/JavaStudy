package base.synchronizedstudy;

/**
 * @Description 两个线程访问的是两个对象的同步方法
 * @Author qi
 * @Date 2021/7/9 22:44
 * @ClassName TwoThreadsTwoObjects
 **/
public class TwoThreadsTwoObjects implements Runnable {

    static TwoThreadsTwoObjects instance1 = new TwoThreadsTwoObjects();
    static TwoThreadsTwoObjects instance2 = new TwoThreadsTwoObjects();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("finished");
    }

    @Override
    public void run() {
        // 虽然锁的都是 this，但其实是两个不同的对象
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 运行结束");
        }
    }
}