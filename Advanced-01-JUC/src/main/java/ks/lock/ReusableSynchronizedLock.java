package ks.lock;

/**
 * @Description synchronized锁
 * @Author qi
 * @Date 2020/5/12 15:54
 * @ClassName ReusableLock
 **/

public class ReusableSynchronizedLock {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();

    }
}

class Phone {

    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " -> sms()");
        // 这里也有锁
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " -> call()");
    }

}