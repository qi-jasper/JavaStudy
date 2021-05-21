package string;

/**
 * @Description String 垃圾回收测试
 * -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 * @Author qi
 * @Date 2021/5/20 11:20
 * @ClassName StringGCTest
 **/
public class StringGCTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String.valueOf(i).intern();
        }
    }
}