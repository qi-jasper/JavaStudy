package stack;

/**
 * @Description
 * @Author qi
 * @Date 2021/3/31 11:15
 * @ClassName SlotTest
 **/
public class SlotTest {

    int count;

    public static void main(String[] args) {

        String a = "hello";
        String b = new String("hello");
        int i = 30;
    }

    public SlotTest() {
        int i = 20;
        int y = 30;
        this.count = 10;
    }

    public void localVar1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localVar2() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量 c 是使用之前已经销毁的变量 b 占用的 Slot 的位置
        int c = a + 1;
    }

}