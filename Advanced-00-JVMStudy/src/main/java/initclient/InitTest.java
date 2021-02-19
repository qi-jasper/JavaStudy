package initclient;

/**
 * @Description
 * @Author qi
 * @Date 2021/1/28 20:46
 * @ClassName InitTest
 **/
public class InitTest {

    static class Father {
        public static int a = 1;
        static {
            a = 2;
        }
    }

    static class Son extends Father {
        public static int b = a;
    }

    public static void main(String[] args) {
        // 会首先加载 Father 类，其次加载 Son 类
        // 打印结果为 2
        System.out.println(Son.b);
    }

}