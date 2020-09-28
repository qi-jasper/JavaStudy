package mooc.cas;

/**
 * @Description 模拟CAS操作，等价代码
 * @Author qi
 * @Date 2020/8/5 10:10
 * @ClassName SimulatedCAS
 **/

public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
