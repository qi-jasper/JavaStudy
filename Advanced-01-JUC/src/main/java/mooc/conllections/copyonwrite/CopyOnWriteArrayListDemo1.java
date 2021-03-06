package mooc.conllections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 对比演示 CopyOnWriteArrayList 可以在迭代的过程中修改数组内容，但是 ArrayList 不行
 * @Author qi
 * @Date 2021/3/1 16:18
 * @ClassName CopyOnWriteArrayListDemo1
 **/
public class CopyOnWriteArrayListDemo1 {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println("list is: " + list);
            String next = iterator.next();
            System.out.println(next);

            // 迭代进行时进行修改
            if (next.equals("2")) {
                list.remove("5");
            }
            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }
}