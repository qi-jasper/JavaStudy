package ks;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/4 10:01
 * @ClassName ks.GetCPU
 **/

public class GetCPU {

    @Test
    public void getCPU() {
        // 获取CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


}
