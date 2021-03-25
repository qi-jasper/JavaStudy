package mooc.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description 最简单的缓存形式：HashMap
 * @Author qi
 * @Date 2021/3/24 16:06
 * @ClassName SimpleCache
 **/
public class SimpleCache {

    /**
     * 给 HashMap 加 final 关键字，属性被声明为 fina 后，该变量只能被赋值一次，
     * 且一旦被赋值，final 的变量就不能再被改变，增强其安全性
     */
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer computer(String userId) {
        Integer result = cache.get(userId);
        // 先检查 HashMap 里有没有保存过之前计算的结果
        if (result == null ) {
            // 如果缓存中找不到，那么就需要立刻计算结果，并保存到 HashMap 中
            try {
                result = doComputer(userId);
                cache.put(userId, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 该方法用于模拟计算
     * @param userId
     * @return
     */
    private Integer doComputer(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) {
        SimpleCache expensiveComputer = new SimpleCache();
        System.out.println("开始计算....");
        Integer result = expensiveComputer.computer("13");
        // 第一次计算会休眠 5 秒，会非常的慢，5 秒后才会显示结果
        System.out.println("第一次计算结果：" + result);
        result = expensiveComputer.computer("13");
        // 第二次因为有了缓存，所以会直接显示结果
        System.out.println("第二次计算结果：" + result);
    }
}