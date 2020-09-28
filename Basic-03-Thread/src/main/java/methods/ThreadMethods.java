package methods;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 16:34
 * @ClassName ThreadMethods
 **/

/*
 * 测试Thread中一些常用的方法
 * 1. start():启动当前线程,调用当前线程的run()
 * 2. run():通常需要重写该方法,里面写上线程需要执行的动作
 * 3. currentThread():返回执行当前代码的线程
 * 4. getName():获取当前线程的名字
 * 5. setName():设置当前线程的名字
 * 6. yeild():释放CPU的执行权
 * 7. join():在线程A中调用线程B的join()方法,则线程A进入阻塞状态,直到线程B完全执行完后,线程A才结束阻塞状态
 *
 * */

/*
 * 线程的优先级
 * 1. MAX_PRIORITY: 10
 * 2. NORM_PRIORITY: 5 --默认优先级
 * 3. MIN_PRIORITY: 1
 * 获取和设置线程的优先级:
 * getPriority()
 * setPriority()*/

class NameThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                // 在这里命名,一定会是在主线程创建完成之后才会看见线程一
//                Thread.currentThread().setName("线程一");
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

// 通过构造器命名
class NameThread2 extends Thread {
    public NameThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "hello world!");
    }
}


public class ThreadMethods {
    public static void main(String[] args) {
        NameThread nameThread = new NameThread();
        // 在这里命名,会看见主线程和线程一的交互
        nameThread.setName("这里是线程一");
        nameThread.start();

        Thread.currentThread().setName("这里是主线程");
        Thread.currentThread().getName();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            if (i == 20) {
                try {
                    nameThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        NameThread2 nameThread2 = new NameThread2("这里是线程二");
        nameThread2.start();
    }
}
