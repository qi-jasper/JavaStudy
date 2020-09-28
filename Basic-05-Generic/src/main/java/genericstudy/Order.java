package genericstudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 自定义的泛型类
 * @Author qi
 * @Date 2020/6/6 18:25
 * @ClassName Order
 **/

public class Order<T> {

    String name;
    int id;

    // 类的内部结构就可以使用类的泛型，T就是类的泛型
    T orderT;

    public Order() {}

    public Order(String name, int id, T orderT) {
        this.name = name;
        this.id = id;
        this.orderT = orderT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 注意：该方法不是泛型方法！！！
    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", orderT=" + orderT +
                '}';
    }

    /**
     * 泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系！！！
     * 即：泛型方法所属的类是不是泛型类都没有关系
     * Java中ArrayList源码中的泛型方法：public <T> T[] toArray(T[] a)
     * 这里可以加static。原因：泛型参数是在调用方法时确定的，并非在实例化时确定的。
     */
    public <E> List<E> copyFromArrayToList(E[] arry) {
        ArrayList<E> list = new ArrayList<E>();

        for (E e : arry) {
            list.add(e);
        }

        return list;
    }
}
