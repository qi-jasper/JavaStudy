package genericstudy;

/**
 * @Description 子类继承父类时，不指定泛型的情况
 * @Author qi
 * @Date 2020/6/11 10:25
 * @ClassName SubOrder1
 **/

// SubOrder<T>仍然是一个泛型类
public class SubOrder1<T> extends Order<T> {

}
