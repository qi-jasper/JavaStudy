package mooc.future;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @Description 演示 get() 的超时方法，需要做超时后的处理，调用 future.cancel()，演示 cancel 传入 true 和 false 的区别，代表是否中断正在执行的任务
 * @Author qi
 * @Date 2021/3/24 14:07
 * @ClassName Timeout
 **/
public class Timeout {

    /**
     * 模拟广告类
     */
    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");
    private static final ExecutorService SERVICE = Executors.newFixedThreadPool(10);

    /**
     * 获取广告任务
     */
    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Sleep 期间被中断了");
                return new Ad("Sleep 期间被中断的默认广告");
            }

            return new Ad("通过网络获取的广告");
        }
    }

    public void printAd() {
        Future<Ad> f = SERVICE.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = f.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            System.out.println("cancel 的结果：" + f.cancel(true));
        }
        SERVICE.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }
}