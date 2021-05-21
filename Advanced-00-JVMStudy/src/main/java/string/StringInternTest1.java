package string;

/**
 * @Description
 * @Author qi
 * @Date 2021/5/20 9:46
 * @ClassName StringInternTest1
 **/
public class StringInternTest1 {
    public static void main(String[] args) {
        String s1 = new String("1").intern();
//        s1.intern();
        String s2 = "1";
        // false
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        // true
        System.out.println(s3 == s4);

        String s5 = new String("1") + new String("1");
        // 在常量池中生成的字符串
        String s6 = "11";
        // 然后 s3 就会从常量池中找，发现有了，就什么事情都不做
        s5.intern();
        System.out.println(s5 == s6);
    }
}