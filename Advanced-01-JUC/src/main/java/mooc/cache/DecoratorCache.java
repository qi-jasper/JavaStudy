package mooc.cache;

import mooc.cache.computable.Computable;
import mooc.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用装饰者模式，给计算器添加缓存功能
 * @Author qi
 * @Date 2021/3/24 17:03
 * @ClassName DecoratorCache
 **/
public class DecoratorCache<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>();

    private final Computable<A, V> c;

    public DecoratorCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制...");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        // 构造函数中传入具体要计算的类
        DecoratorCache<String, Integer> expensiveComputer = new DecoratorCache<>(new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果：" + result);
        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果：" + result);
    }
}