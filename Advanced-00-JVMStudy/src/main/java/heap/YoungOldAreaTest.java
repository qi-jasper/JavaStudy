package heap;

/**
 * @Description 测试：大对象直接进入老年代;
 * 设置参数：-XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @Author qi
 * @Date 2021/5/15 16:57
 * @ClassName YoungOldAreaTest
 **/
public class YoungOldAreaTest {
    public static void main(String[] args) {
        // 20M
        byte[] buffer = new byte[1024 * 1024 * 20];
    }
}