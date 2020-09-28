package create;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 14:29
 * @ClassName CreateByRunnable
 **/

/*
 * 创建三个窗口卖票,总票数为100
 */

/*class Window extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":the ticket number is :" + ticket);
                ticket--;
            }
        }
    }
}

public class WindowsThread {
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();

        window1.start();
        window2.start();
        window3.start();
    }
}*/

/*
 * 创建多线程的方式2:实现Runnable接口的方式
 * 1. 创建一个实现了Runnable的接口类
 * 2. 实现类去实现Runnable中的抽象方法:run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread类的构造器中,创建Thread类的对象
 * 5. 通过Thread类的对象调用start()
 */

//  1. 创建一个实现了Runnable的接口类
class Window implements Runnable {
    // 2. 实现类去实现Runnable中的抽象方法:run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

public class CreateByRunnable {

    @Test
    public void windowTest() {
        // 3. 创建实现类的对象
        Window window = new Window();
        // 4. 将此对象作为参数传递到Thread类的构造器中,创建Thread类的对象
        Thread t = new Thread(window);
        // 5. 通过Thread类的对象调用start()
        t.start();
        // 再启动一个线程
        Thread t2 = new Thread(window);
        t2.start();
    }
}
