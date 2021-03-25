package mooc.cache;

import mooc.cache.computable.Computable;
import mooc.cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description 使用 Future + CAS 避免重复计算
 * @Author qi
 * @Date 2021/3/24 17:25
 * @ClassName FutureCasCache
 **/
public class FutureCasCache<A, V> implements Computable<A, V> {

    /**
     * value 类型改为 Future
     */
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public FutureCasCache(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制...");

        // 使用 Future 避免重复计算
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> callable = () -> c.compute(arg);

            FutureTask<V> ft = new FutureTask<>(callable);
            /**
             * putIfAbsent()：往内添加元素，如果元素不存在，就不会再往里放值，会返回当前的值
             * 即使是两个线程同时往里添加，因为 ConcurrentHashMap 的原子性，一定是有先后的
             */
            f = cache.putIfAbsent(arg, ft);
            // if 中判断 cache.putIfAbsent() 是否返回为空
            if (f == null) {
                f = ft;
                // 在计算之前先进行 put 操作，这样就可以避免重复计算，后面再有线程需要获取时，就能够获取到，但是因为计算结果还没有出来，所以会先阻塞
                // 当计算完成之后，就会取消阻塞状态
                cache.put(arg, ft);
                System.out.println("从 FutureTask 调用了计算函数");
                // 开始计算，内部执行 callable
                ft.run();
            }
        }

        return f.get();
    }

    public static void main(String[] args) throws Exception {
        // 构造函数中传入具体要计算的类
        FutureCasCache<String, Integer> expensiveComputer = new FutureCasCache<>(new ExpensiveFunction());

        new Thread(() -> {
            Integer result = null;
            try {
                result = expensiveComputer.compute("666");
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Integer result = null;
            try {
                result = expensiveComputer.compute("667");
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Integer result = null;
            try {
                result = expensiveComputer.compute("666");
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}