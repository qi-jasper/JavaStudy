import org.junit.Test;

import java.util.HashMap;

/**
 * @Description HashMap数据结构
 * @Author qi
 * @Date 2020/5/25 21:12
 * @ClassName DataStruct
 **/

public class HashMapDataStruct {

    @Test
    public void structTest() {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("qi", 18);
        hm.put("wei", 18);
        hm.put("qi", 20);
        System.out.println(hm);
    }

}
