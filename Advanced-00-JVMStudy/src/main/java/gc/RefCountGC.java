package gc;

/**
 * @Description 代码演示 Java 中在标记阶段没有使用引用计数算法
 * -XX:+PrintGCDetails
 * @Author qi
 * @Date 2021/5/20 15:09
 * @ClassName RefCountGC
 **/
public class RefCountGC {
    // 这个成员属性的唯一作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];
    // 引用
    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        // 显示的执行垃圾收集行为，判断 obj1 和 obj2 是否被回收？
        // System.gc();
    }
}