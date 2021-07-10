package base.synchronizedstudy;

/**
 * @Description 情况二：证明可重入不要求是同一个方法；可重入粒度测试：调用类内另外的方法
 * @Author qi
 * @Date 2021/7/10 14:47
 * @ClassName SynchronizedOtherMethod
 **/
public class SynchronizedOtherMethod {

    public static void main(String[] args) {
        SynchronizedOtherMethod s = new SynchronizedOtherMethod();
        s.method1();
    }

    public synchronized void method1() {
        System.out.println("This is method1()...");
        method2();
    }

    public synchronized void method2() {
        System.out.println("This is method2()...");
    }
}