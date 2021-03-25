package mooc.cache;

import mooc.cache.computable.Computable;
import mooc.cache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description 出于安全性考虑，缓存需要设置有效期，到期自动失效，否则如果缓存一直不失效，那么会带来缓存不一致等问题
 * @Author qi
 * @Date 2021/3/24 17:25
 * @ClassName TimelyCache
 **/
public class TimelyCache<A, V> implements Computable<A, V> {

    /**
     * value 类型改为 Future
     */
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public TimelyCache(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 普通计算方法
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
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

            // 3 种异常之所以要用
            try {
                return f.get();
            } catch (CancellationException e) {
                // Future 在计算过程中被 cancel 取消，就会抛出 CancellationException
                System.out.println("被取消了");
                // 抛出异常，移除错误值
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                // 抛出异常，移除错误值
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误，需要重试...");
                // 抛出异常，移除错误值
                cache.remove(arg);
            }
        }
    }

    /**
     * 使用线程池来实现超时时间问题
     */
    public final static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(5);

    /**
     * 重载方法，设置超时时间
     * @param arg
     * @param expire 超时时间
     * @return
     */
    public V compute(A arg, long expire) throws InterruptedException {
        if (expire > 0) {
            EXECUTOR_SERVICE.schedule(() -> {
                // 清除的方法
                expire(arg);
            }, expire, TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    /**
     * 清除缓存
     * @param key
     */
    public synchronized void expire(A key) {
        // 检查缓存中是否还存在 key
        Future<V> future = cache.get(key);
        if (future != null) {
            // 检查任务是否完成，未完成则取消任务
            if (!future.isDone()) {
                System.out.println("任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }
    }

    /**
     * 缓存过期时间随机
     * @param arg
     * @return
     */
    public V computeRandomExpire(A arg) throws InterruptedException {
        long randomExpire = (long) (Math.random() * 1000);
        return compute(arg, randomExpire);
    }



    public static void main(String[] args) throws Exception {
        // 构造函数中传入具体要计算的类
        TimelyCache<String, Integer> expensiveComputer = new TimelyCache<>(new MayFail());

        new Thread(() -> {
            Integer result = null;
            try {
                result = expensiveComputer.compute("666", 5000L);
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

        // 模拟过期，再次计算，查看结果
        Thread.sleep(6000L);
        Integer result = expensiveComputer.compute("666");
        System.out.println("主线程的计算结果：" + result);
    }
}