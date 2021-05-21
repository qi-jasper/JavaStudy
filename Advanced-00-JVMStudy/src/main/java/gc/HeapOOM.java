package gc;

import java.util.ArrayList;

/**
 * @Description 手动制造 OOM
 * 设置参数：-Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
 * @Author qi
 * @Date 2021/5/21 14:38
 * @ClassName HeapOOM
 **/
public class HeapOOM {
    // 创建 1M 的数组
    byte [] buffer = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {
        ArrayList<HeapOOM> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new HeapOOM());
                count++;
            }
        } catch (Exception e) {
            System.out.println("count:" + count);
            e.getStackTrace();
        }
    }
}