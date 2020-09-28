package ks.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/12 13:10
 * @ClassName ABADemo
 **/

public class ABADemo {

    // 可能存在问题的方式
    /*public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // 期望的线程
        atomicInteger.compareAndSet(2020, 2021);
        System.out.println(atomicInteger.get());

        // 捣乱的线程
        atomicInteger.compareAndSet(2021, 2020);
        System.out.println(atomicInteger.get());

        // 期望的线程
        atomicInteger.compareAndSet(2020, 666);
        System.out.println(atomicInteger.get());
    }*/

    // 修改后的方式
    public static void main(String[] args) {

        // AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题，Integer范围是-128~127
        // 正常在业务操作，泛型里面比较的都是一个个对象
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("a1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);

            System.out.println("a2=>"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));

            System.out.println("a3=>"+atomicStampedReference.getStamp());
        },"a").start();

        // 乐观锁的原理相同！
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("b1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("b2=>"+atomicStampedReference.getStamp());
        },"b").start();

    }
}
