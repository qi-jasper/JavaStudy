package base.synchronizedstudy;

/**
 * @Description 情况一：证明同一个方法是可重入的；可重入粒度测试：递归调用本方法
 * @Author qi
 * @Date 2021/7/10 14:42
 * @ClassName SynchronizedRecursion
 **/
public class SynchronizedRecursion {

    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion s = new SynchronizedRecursion();
        s.method();
    }

    private synchronized void method() {
        System.out.println("This is method(), a = " + a);
        if (a == 0) {
            a++;
            method();
        }
    }
}