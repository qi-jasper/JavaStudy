package base.threadcoreknowledge.startthreads;

/**
 * @Description 演示不能两次调用 start() 方法，否则会报错
 * @Author qi
 * @Date 2021/5/30 19:30
 * @ClassName CantStartTwice
 **/
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}