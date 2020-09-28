package example;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 16:27
 * @ClassName ThreadCommunication
 **/

/*线程通信的例子：使用两个线程打印1-100，线程1和线程2交替打印
 *
 * wait()：一旦执行此方法，当前线程就进入阻塞状态，同时释放同步监视器
 * notify()：一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程被wait，则唤醒优先级高的那个
 * notifyAll()：一旦执行此方法，就会唤醒所有被wait的线程
 *
 * Tips：1. wait(), notify(), notifyAll()三个方法必须使用在同步代码块或者同步方法中
 *       2. wati(), notify(), notifyAll()三个方法是定义在java.lang.Object方法中的。
 *
 * sleep() 和 wait()的异同？
 * 1.相同点：一旦执行方法，都可以是的当前的线程进入阻塞状态
 * 2.不同点：1)两个方法声明的位置不同：sleep()声明在Thread类中，wait()声明在Object类中
 *          2)调用要求不同，sleep()可以在任何需要的场景下使用，wait()只能在同步代码块中使用
 *          3)如果两个方法都使用在同步代码块或者同步方法中，sleep()不会释放锁，wait()会释放锁
 * */

class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {

                // 唤醒wait的线程（因为只有两个线程，所以使用notify，如果有三个线程，则使用notifyAll，否则就是按照优先级唤醒
                notify();

                if(number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        // 使得如下调用wait()方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);

        t1.setName("一");
        t2.setName("二");

        t1.start();
        t2.start();
    }
}
