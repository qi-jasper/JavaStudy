package base.synchronizedstudy;

/**
 * @Description 类锁的第二种形式：synchronized(*.class)
 * @Author qi
 * @Date 2021/7/9 21:44
 * @ClassName SynchronizedClassClass
 **/
public class SynchronizedClassClass implements Runnable{

    static SynchronizedClassClass instance1 = new SynchronizedClassClass();
    static SynchronizedClassClass instance2 = new SynchronizedClassClass();

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
        method();
    }

    /**
     * 使用本类的 SynchronizedClassClass.class 来作为所
     */
    public void method() {
        synchronized (SynchronizedClassClass.class) {
            System.out.println("我是类锁的第二种形式：synchronized(*.class)，我叫：" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 运行结束");
        }
    }
}