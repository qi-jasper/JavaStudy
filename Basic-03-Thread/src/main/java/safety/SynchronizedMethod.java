package safety;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 15:54
 * @ClassName SynchronizedMethod
 **/

/*实现方式二：同步方法
 * 解决实现Runnable线程安全问题
 *
 * 写法：在方法前加synchronized关键字，但是不建议将run方法写成同步，所以专门写一个方法*/

class WindowSafety3 implements Runnable {
    private int ticket = 100;

    // 将共享数据的内容，专门写成一个方法，加synchronized
    // 同步监视器：this，这里没有锁的概念了
    private synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ": 票号为" + ticket);

            ticket--;
        }
    }

    @Override
    public void run() {
        while (true) {
            show();
        }

    }
}

public class SynchronizedMethod {

    public static void main(String[] args) {
        WindowSafety3 wsf = new WindowSafety3();

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
