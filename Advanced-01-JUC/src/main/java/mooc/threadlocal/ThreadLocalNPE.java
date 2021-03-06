package mooc.threadlocal;

/**
 * @Description
 * @Author qi
 * @Date 2020/6/23 13:55
 * @ClassName ThreadLocalNPE
 **/
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();

        // 如果不set，在这里直接get的话，就会出现null值，如果上面的get()方法是long类型，则会出现空指针异常。这是因为泛型中写的是包装类型Long
        // System.out.println(threadLocalNPE.get());

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread1.start();
    }
}