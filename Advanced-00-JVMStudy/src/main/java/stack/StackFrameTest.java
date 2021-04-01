package stack;

/**
 * @Description 栈帧代码演示
 * @Author qi
 * @Date 2021/3/26 16:57
 * @ClassName StackFrameTest
 **/
public class StackFrameTest {

    public static void main(String[] args) {
        method01();
    }

    private static int method01() {
        System.out.println("method1() 开始执行...");
        int i = method02();
        System.out.println("method1() 执行结束...");
        return i;
    }

    private static int method02() {
        System.out.println("method2() 开始执行...");
        int i = method03();
        System.out.println("method2() 即将结束...");
        return i;
    }

    private static int method03() {
        System.out.println("method3() 开始执行...");
        int i = 30;
        System.out.println("method3() 即将结束...");
        return i;
    }

}