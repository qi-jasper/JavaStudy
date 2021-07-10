package base.synchronizedstudy;

/**
 * @Description 情况三：证明可重入不要求是同一个类中的；可重入粒度测试：调用父类的方法
 * @Author qi
 * @Date 2021/7/10 14:50
 * @ClassName SynchronizedSuperClass
 **/
public class SynchronizedSuperClass {

    public synchronized void doSomething() {
        System.out.println("这里是父类中的方法...");
    }
}

class TestClass extends SynchronizedSuperClass {

    public static void main(String[] args) {
        TestClass s = new TestClass();
        s.doSomething();
    }

    @Override
    public synchronized void doSomething() {
        System.out.println("这里是子类中的方法...");
        super.doSomething();
    }
}