package ks.singleton;

/**
 * @Description 饿汉式单例
 * @Author qi
 * @Date 2020/5/12 10:38
 * @ClassName Hungry
 **/

public class Hungry {

    // 浪费内存
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    private static Hungry getInstance() {
        return HUNGRY;
    }

}
