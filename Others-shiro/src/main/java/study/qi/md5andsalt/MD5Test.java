package study.qi.md5andsalt;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/20 19:29
 * @ClassName MD5Test
 **/
public class MD5Test {

    public static void main(String[] args) {

        // 创建一个 md5 算法，toHex() 方法：转为 md5 加密后的16进制
        /*Md5Hash md5Hash = new Md5Hash();

        md5Hash.setBytes("123".getBytes());

        String s = md5Hash.toHex();
        System.out.println(s);*/

        // md5 正确的使用方式
        Md5Hash md5Hash = new Md5Hash("123");
        // 202cb962ac59075b964b07152d234b70
        System.out.println("单纯地对密码'123'加密后的16进制：" + md5Hash.toHex());

        // 使用 md5 + salt 处理
        Md5Hash md5Hash1 = new Md5Hash("123", "qiqiqi");
        // e43a87bc938a5f931a899e76fcf446af
        System.out.println("使用 md5 + salt 对密码'123'加密后的16进制：" + md5Hash1.toHex());

        // 使用 md5 + salt + hash 散列，第三个参数表示散列次数
        Md5Hash md5Hash2 = new Md5Hash("123", "qiqiqi", 1024);
        // 4f92e9b3a0757fbae5a196fa40e33fc1
        System.out.println("使用 md5 + salt + hash 散列对密码'123'加密后的16进制：" + md5Hash2.toHex());

    }
}