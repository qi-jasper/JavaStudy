package ks.singleton;

/**
 * @Description DCL懒汉式单例模式
 * @Author qi
 * @Date 2020/5/12 10:48
 * @ClassName DCLazy
 **/

public class DCLazy {

    private DCLazy() {
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static DCLazy lazy;

    // 使用双重检测锁的懒汉式，即DCL懒汉式，来解决多线程问题
    private static DCLazy getInstance() {
        if (lazy == null) {
            synchronized (DCLazy.class) {
                if (lazy == null) {
                    // 不是一个原子性操作
                    lazy = new DCLazy();
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把对象指向空间
                     *
                     * 由于指令重排，可能导致一些问题，两个线程都来使用，但是还没完成构造，所以在上方使用volatile避免指令重排
                     */
                }
            }
        }

        return lazy;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            new Thread(() -> {
                DCLazy.getInstance();
            }).start();
        }
    }
}
