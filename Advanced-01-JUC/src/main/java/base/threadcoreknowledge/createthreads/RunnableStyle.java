package base.threadcoreknowledge.createthreads;

/**
 * @Description 用 Runnable 方式创建线程
 * @Author qi
 * @Date 2021/5/28 17:02
 * @ClassName RunnableStyle
 **/
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("用 Runnable 的方式创建线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}