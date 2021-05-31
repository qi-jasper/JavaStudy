package base.threadcoreknowledge.createthreads;

/**
 * @Description 使用继承 Thread 方式创建线程
 * @Author qi
 * @Date 2021/5/28 17:08
 * @ClassName ThreadStyle
 **/
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("使用继承 Thread 类的方式创建线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}