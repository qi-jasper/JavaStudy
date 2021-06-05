package base.threadcoreknowledge.stopthreads.volatiledemo;

/**
 * @Description 演示用 volatile 的局限-part1：看似可行
 * @Author qi
 * @Date 2021/6/5 19:47
 * @ClassName WrongWayVolatile
 **/
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);

        r.canceled = true;
    }
}