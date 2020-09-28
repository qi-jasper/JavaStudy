package ks.singleton;

import java.lang.reflect.Constructor;

/**
 * @Description 懒汉式单例
 * @Author qi
 * @Date 2020/5/12 10:40
 * @ClassName Lazy
 **/

public class Lazy {

    private Lazy() {

        synchronized (Lazy.class) {
            if (lazy != null) throw new RuntimeException("不要试图用反射破坏异常！");
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private static Lazy lazy;

    // 存在问题，单线程是可以的，但是多线程会存在安全问题！！！
    private static Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }

        return lazy;
    }

    public static void main(String[] args) throws Exception {
        /*for (int i = 0; i < 10 ; i++) {
            new Thread(() -> {
                Lazy.getInstance();
            }).start();
        }*/

        // 通过反射破坏单例
        Lazy instance = Lazy.getInstance();
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        Lazy instance1 = declaredConstructor.newInstance();

        /**
         * 输出的结果不相同，说明单例模式被破坏，可以在构造器中添加以下代码
         *  synchronized (Lazy.class) {
         *      if (lazy != null) throw new RuntimeException("不要试图用反射破坏异常！");
         *  }
         *
         *  但是仍有方式能够破坏，即，两个对象都通过反射创建：
         *  Lazy instance1 = declaredConstructor.newInstance();
         *  Lazy instance2 = declaredConstructor.newInstance();
         *  这个时候可以添加标志位
         */

        System.out.println(instance);
        System.out.println(instance1);
    }

}
