package initclient;

/**
 * @Description
 * @Author qi
 * @Date 2021/1/26 22:18
 * @ClassName ClInitTest
 **/
public class ClInitTest {

    public static int num = 1;

    static {
        num = 2;
        number = 20;

        System.out.println(num);
        // 报错:Illegal forward reference，非法的前向引用
        // System.out.println(number);
    }

    /**
     * 在 Linking 的 Prepare 阶段时：number 已经赋值为 0 了
     * 到了 Initialization 阶段时：会按照代码顺序，给 number 赋值，即先赋值为 20，然后再赋值为 10
     */
    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }
}