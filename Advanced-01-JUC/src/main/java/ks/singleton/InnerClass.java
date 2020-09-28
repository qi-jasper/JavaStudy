package ks.singleton;

/**
 * @Description 静态内部类实现
 * @Author qi
 * @Date 2020/5/12 10:58
 * @ClassName InnerClass
 **/

class Inner {

    private Inner() {

    }

    private static class InnerInstance {
        private static final Inner INSTANCE = new Inner();
    }

    public static Inner getInstance() {
        return InnerInstance.INSTANCE;
    }
}

public class InnerClass {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Inner.getInstance();
            }).start();
        }
    }
}