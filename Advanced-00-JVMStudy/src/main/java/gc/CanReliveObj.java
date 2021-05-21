package gc;

/**
 * @Description 测试 Object 类中 finalize() 方法
 * @Author qi
 * @Date 2021/5/20 16:51
 * @ClassName CanReliveObj
 **/
public class CanReliveObj {

    /**
     * 类变量，属于 GC Roots 的一部分
     */
    public static CanReliveObj canReliveObj;

    /**
     * 此方法只能被调用一次
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的 finalize() 方法");
        // 当前待回收的对象在 finalize() 方法中与引用链上的任何一个对象建立了联系
        canReliveObj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        canReliveObj = new CanReliveObj();

        canReliveObj = null;
        // 调用垃圾回收期
        System.gc();
        System.out.println("-----------------第一次 GC 操作------------");
        // 因为 Finalizer 线程的优先级比较低，暂停 2 秒，以等待它
        Thread.sleep(2000);
        if (canReliveObj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }

        System.out.println("-----------------第二次 GC 操作------------");
        canReliveObj = null;
        System.gc();
        // 下面代码和上面代码是一样的，但是 canReliveObj 却自救失败了
        Thread.sleep(2000);
        if (canReliveObj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }
    }
}