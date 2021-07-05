package base.threadcoreknowledge.sixstates;

/**
 * @Description 展示线程的 New、Runnable、Terminated 状态。即使是正在运行，也是 Runnable 状态，而不是 Running
 * @Author qi
 * @Date 2021/6/30 14:49
 * @ClassName NewRunnableTerminated
 **/
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());

        // 打印出 NEW 的状态
        System.out.println(thread.getState());

        thread.start();

        System.out.println(thread.getState());

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 打印出 Runnable 的状态，即使是正在运行，也是 Runnable，而不是 Running
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 打印出 Terminated 状态
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000 ; i++) {
            System.out.println(i);
        }
    }
}