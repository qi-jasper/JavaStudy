package mooc.threadpool;

/**
 * @Description 模拟任务进程
 * @Author qi
 * @Date 2020/6/15 15:18
 * @ClassName Task
 **/
public class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

}