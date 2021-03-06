package mooc.conllections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 演示 ConcurrentHashMap 线程不安全的情况——组合操作
 * @Author qi
 * @Date 2021/3/1 14:39
 * @ClassName OptionsNotSafe
 **/
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("qi", 0);
        Thread t1 = new Thread(new OptionsNotSafe(), "thread-1");
        Thread t2 = new Thread(new OptionsNotSafe(), "thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000 ; i++) {
            while (true) {
                Integer score = scores.get("qi");
                Integer newScore = score + 1;
                // replace 方法返回一个 boolean 值，表示该操作是否成功
                boolean b = scores.replace("qi", score, newScore);
                System.out.println(b);
                if (b) {
                    break;
                }
            }
        }
    }
}