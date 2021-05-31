package base.threadcoreknowledge.startthreads;

/**
 * @Description 对比 start() 和 run() 启动线程的方式
 * @Author qi
 * @Date 2021/5/29 23:07
 * @ClassName StartAndRunMethod
 **/
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        runnable.run();

        new Thread(runnable).start();
    }
}