package mooc.conllections.predecessor;

import java.util.*;

/**
 * @Description 演示Collections.synchronizedList(new ArrayList<E>())
 * @Author qi
 * @Date 2020/8/6 10:06
 * @ClassName SynList
 **/
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        System.out.println(list.get(0));


        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }
}