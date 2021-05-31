package base.threadcoreknowledge.createthreads;

/**
 * @Description 同时使用继承 Thread 类和实现 Runnable 接口的方式来创建线程会发生什么？
 * @Author qi
 * @Date 2021/5/28 17:22
 * @ClassName BothRunnableThread
 **/
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("来自 Runnable...");
            }
        }) {
            @Override
            public void run() {
                System.out.println("来自 Thread...");
            }
        }.start();
    }
}