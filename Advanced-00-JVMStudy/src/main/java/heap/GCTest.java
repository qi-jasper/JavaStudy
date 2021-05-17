package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 不断地创建和拼接字符串
 * @Author qi
 * @Date 2021/5/15 16:31
 * @ClassName GCTest
 **/
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "mogu blog";
            while(true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}