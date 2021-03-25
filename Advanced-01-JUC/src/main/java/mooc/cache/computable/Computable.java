package mooc.cache.computable;

/**
 * @Description 计算接口，用来代表耗时计算，每个计算器都要实现这个接口，这样就可以无侵入实现缓存功能
 * @Author qi
 * @Date 2021/3/24 17:00
 * @InterfaceName Computable
 **/
public interface Computable<A,V> {

    V compute(A arg) throws  Exception;

}