package mooc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 乐观锁与悲观锁的举例
 * @Author qi
 * @Date 2020/7/6 09:21
 * @ClassName PessimismOptimismLock
 **/
public class PessimismOptimismLock {

    public static void main(String[] args) {
        // 原子类
        AtomicInteger ato = new AtomicInteger();
        ato.incrementAndGet();
    }

    // 典型的synchronized悲观锁
    public synchronized void testMethod() { }
}