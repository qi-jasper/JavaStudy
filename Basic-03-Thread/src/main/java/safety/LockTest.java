package safety;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 16:04
 * @ClassName LockTest
 **/

/*
 * 解决线程安全问题的方式三：Lock锁
 *
 * synchronized与Lock的异同？
 * 同：二者都可以解决线程安全难问题
 * 异：synchronized机制在执行完同步代码后，自动释放同步监视器
 * Lock需要手动启动同步，手动地结束同步。
 *
 * 用synchronized比较多，但是建议使用Lock
 *
 */

class Window implements Runnable {
    private int ticket = 100;

    // 1. 实例化Reentranlock
    private ReentrantLock lock = new ReentrantLock(true); // 设置为true则是公平的，不写就是false，即非公平的

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 调用lock()方法
                lock.lock();

                if(ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 3. 调用解锁方法
                lock.unlock();
            }
        }

    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("线程一");
        t2.setName("线程二");
        t3.setName("线程三");

        t1.start();
        t2.start();
        t3.start();
    }
}
