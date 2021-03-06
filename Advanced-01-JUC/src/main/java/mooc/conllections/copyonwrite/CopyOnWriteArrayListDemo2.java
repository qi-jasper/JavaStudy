package mooc.conllections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 对比两个迭代器
 * @Author qi
 * @Date 2021/3/2 9:57
 * @ClassName CopyOnWriteArrayListDemo2
 **/
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});

        System.out.println(list);

        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);

        Iterator<Integer> itr2 = list.iterator();

        // 对比结果可以看出：迭代器所能拿到的数据取决于迭代器初始化的时间，而不是却决于它的迭代时间
        itr1.forEachRemaining(System.out::println);
        itr2.forEachRemaining(System.out::println);


    }
}