package mooc.immutable;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/6 09:17
 * @ClassName FinalStringDemo1
 **/

public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        // true
        System.out.println((a == c));
        // false
        System.out.println((a == e));
    }
}