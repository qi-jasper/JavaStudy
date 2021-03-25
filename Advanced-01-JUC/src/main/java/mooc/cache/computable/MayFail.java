package mooc.cache.computable;

import java.io.IOException;

/**
 * @Description 一个可能抛出异常的计算类
 * @Author qi
 * @Date 2021/3/25 11:44
 * @ClassName MayFail
 **/
public class MayFail implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        // 使用随机数值来模拟 IO 异常
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("读取失败");
        }

        // 模拟计算
        Thread.sleep(3000);

        return Integer.valueOf(arg);
    }
}