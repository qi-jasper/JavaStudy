package base.threadcoreknowledge.stopthreads;

/**
 * @Description 如果 while 里面放 try/catch，会导致中断失效
 * @Author qi
 * @Date 2021/6/5 14:38
 * @ClassName CantInterrupt
 **/
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000) {
                if (num % 100 == 0) {
                    System.out.println(num + "是 100 的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}