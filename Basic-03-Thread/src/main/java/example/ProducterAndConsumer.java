package example;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 16:26
 * @ClassName ProducterAndConsumer
 **/

/*线程通信的应用：经典例题：生产者/消费者问题
 *
 * 生产者（Productor）将产品交给店员（Clerk），而消费者（Consumer）从店员处取走产品，
 * 店员以此只能持有固定数量的产品（如：20），如果生产者试图生产更多的产品，店员会叫生产者停下，
 * 如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员会告诉消费者等一下，如果店中
 * 有产品了再通知消费者来取走产品。
 *
 * 分析：
 * 1. 是否是多线程问题？    是， 生产者线程，消费者线程
 * 2. 是否有线程安全问题？  是，  共享数据问题：产品
 * 3. 如何解决线程安全问题？    三种同步机制解决
 * 4. 是否涉及到线程的通信问题？    是
 * */

class Clerk {

    private int productCount = 0;

    // 生产产品
    public synchronized void produceProduct() {
        if(productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + "开始生产第"
                    + productCount + "产品");

            notify(); // 唤醒消费者
        } else {
            try {
                wait(); // 等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费产品
    public synchronized void consumerConsume() {
        if(productCount > 0) {
            System.out.println(Thread.currentThread().getName() + "开始消费第"
                    + productCount + "产品");

            notify(); // 唤醒生产者
        } else {
            try {
                wait(); // 等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Producer
class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始生产产品....");
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

// Consumer
class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始消费...");
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumerConsume();
        }
    }
}

public class ProducterAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");

        Consumer c1 = new Consumer(clerk);
        c1.setName("消费者1");

        Consumer c2 = new Consumer((clerk));
        c1.setName("消费者2");

        // 启动线程
        p1.start();
        c1.start();
        c2.start();
    }
}
