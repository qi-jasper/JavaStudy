package mooc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 模拟 100 米跑步，5 名选手都准备好了，等裁判枪响，所有人同时开始跑步
 * @Author qi
 * @Date 2021/3/6 16:38
 * @ClassName CountDownLatchDemo2
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 这里传入 1 ，传入的参数代表的是，有几次倒数，只有一个裁判员，所以只有一次倒数
        CountDownLatch begin = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6 ; i++) {
            int no = i;
            Runnable runnable = () ->  {
                System.out.println("No." + no + " 准备完毕，等待发枪");
                try {
                    begin.await();
                    System.out.println("No." + no + " 开始跑步");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }

        // 模拟裁判员在检查...
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();
    }
}