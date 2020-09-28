package ks.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description CAS:compare and swap
 * @Author qi
 * @Date 2020/5/12 11:38
 * @ClassName CASDemo
 **/

public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 比较并交换，如果期望的值拿到了，则更新
        atomicInteger.compareAndSet(2020, 2021);
        System.out.println(atomicInteger.get());
    }
}
