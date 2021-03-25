package mooc.cache;

import mooc.cache.computable.Computable;
import mooc.cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description 使用 Future 避免重复计算
 * @Author qi
 * @Date 2021/3/24 17:25
 * @ClassName FutureCache
 **/
public class FutureCache<A, V> implements Computable<A, V> {

    /**
     * value 类型改为 Future
     */
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public FutureCache(Computable<A, V> c) {
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
            // 如果为空，进入 if 语句计算，应当再赋值给 f，不然外面的 return 语句会返回空
            f = ft;
            // 在计算之前先进行 put 操作，这样就可以避免重复计算，后面再有线程需要获取时，就能够获取到，但是因为计算结果还没有出来，所以会先阻塞
            // 当计算完成之后，就会取消阻塞状态
            cache.put(arg, ft);
            System.out.println("从 FutureTask 调用了计算函数");
            // 开始计算，内部执行 callable
            ft.run();
        }

        return f.get();
    }

    public static void main(String[] args) throws Exception {
        // 构造函数中传入具体要计算的类
        FutureCache<String, Integer> expensiveComputer = new FutureCache<>(new ExpensiveFunction());

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