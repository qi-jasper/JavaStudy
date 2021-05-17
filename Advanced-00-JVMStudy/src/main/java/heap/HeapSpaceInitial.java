package heap;

/**
 * @Description
 * @Author qi
 * @Date 2021/5/15 16:30
 * @ClassName HeapSpaceInitial
 **/
public class HeapSpaceInitial {
    public static void main(String[] args) throws InterruptedException {
        // 返回 Java 虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回 Java 虚拟机试图使用的最大堆内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        Thread.sleep(1000000);
        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xmx:" + maxMemory + "M");
    }
}