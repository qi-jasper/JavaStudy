package safety;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 15:47
 * @ClassName SynchronizedRunnable
 **/


/*实现方式一：同步代码块
解决实现Runnable接口的线程安全问题
synchronized(同步监视器) {
    需要被同步的代码(Tips:操作共享数据的代码，即为需要被同步的代码)
}   // 同步监视器，俗称，锁，任何一个类的对象，都可以充当锁。
*/

/*解决了线程安全的问题， --好处
 * 局限性--操作同步代码时，只能有一个线程参与，其他线程等待，相当于单线程，效率低。*/

class WindowSafety implements Runnable {
    private int ticket = 100;
    // 同步锁只能放在这个地方，所有的线程共用一把锁
    //
//    Object obj = new Object();


    @Override
    public void run() {

        while(true) {
//            synchronized (obj) {
            synchronized (this) {   // 使用this表示当前对象，这样就不需要再new一个新的对象,this代表唯一的WindowSafety对象wsf

                if(ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": 票号为" + ticket);

                    ticket --;
                } else {
                    break;
                }
            }
        }

    }
}

public class SynchronizedRunnable {

    public static void main(String[] args) {
        WindowSafety wsf = new WindowSafety();

        Thread td1 = new Thread(wsf);
        Thread td2 = new Thread(wsf);
        Thread td3 = new Thread(wsf);

        td1.setName("线程一");
        td2.setName("线程二");
        td3.setName("线程三");

        td1.start();
        td2.start();
        td3.start();


    }
}
