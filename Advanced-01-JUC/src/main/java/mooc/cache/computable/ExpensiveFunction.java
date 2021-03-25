package mooc.cache.computable;

/**
 * @Description 耗时计算实现类，实现了 Computable 接口，本身不具备缓存能力，不考虑缓存问题
 * @Author qi
 * @Date 2021/3/24 17:01
 * @ClassName ExpensiveFunction
 **/
public class ExpensiveFunction implements Computable<String, Integer>{

    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(5000);
        return Integer.valueOf(arg);
    }
}