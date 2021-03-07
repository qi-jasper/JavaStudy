package mooc.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 模拟 100 米跑步，5 名选手都准备好了，等裁判枪响，所有人同时开始跑步。当所有人都到终点后，比赛结束
 * @Author qi
 * @Date 2021/3/6 18:01
 * @ClassName CountDownLatchDemo1And2
 **/
public class CountDownLatchDemo1And2 {
    public static void main(String[] args) throws InterruptedException {
        // 这个是开始时使用的，这里传入 1 ，传入的参数代表的是，有几次倒数，只有一个裁判员，所以只有一次倒数
        CountDownLatch begin = new CountDownLatch(1);
        // 这个是结束时使用的，有 5 个运动员，所以相当于倒数 5 次
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6 ; i++) {
            int no = i;
            Runnable runnable = () ->  {
                System.out.println("No." + no + " 准备完毕，等待发枪");
                try {
                    begin.await();
                    System.out.println("No." + no + " 开始跑步");
                    // 模拟跑步的场景
                    Thread.sleep((long) (Math.random() * 1000));
                    // 执行以下语句代表跑到终点
                    System.out.println("No." + no + " 抵达终点");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 运动员到终点后通知裁判已经到达终点，数量减一
                    end.countDown();
                }
            };
            service.submit(runnable);
        }

        // 模拟裁判员在检查...
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();

        // 线程进入阻塞状态，等 5 个运动员都抵达终点后结束
        end.await();
        System.out.println("所有运动员都抵达终点，比赛结束");
    }
}