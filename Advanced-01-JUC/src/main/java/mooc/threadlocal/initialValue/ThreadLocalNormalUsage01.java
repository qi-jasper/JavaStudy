package mooc.threadlocal.initialValue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 10个线程打印各自的SimpleDateFormat
 * @Author qi
 * @Date 2020/6/18 18:51
 * @ClassName ThreadLocalNormalUsage01
 **/
public class ThreadLocalNormalUsage01 {

    public String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }

    }

}