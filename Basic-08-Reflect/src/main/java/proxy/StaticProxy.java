package proxy;

/**
 * @Description 静态代理举例
 * @Author qi
 * @Date 2020/9/30 9:37
 **/
public class StaticProxy {
    public static void main(String[] args) {
        // 创建被代理类对象
        NikeFactory nikeFactory = new NikeFactory();

        // 创建代理类对象，传入被代理类对象
        ProxyFactory proxyFactory = new ProxyFactory(nikeFactory);

        // 通过代理类对象去调用的是被代理类对象的方法
        proxyFactory.produceCloth();
    }
}

/**
 * 接口
 */
interface ClothFactory {
    void produceCloth();
}

/**
 * 代理类
 */
class ProxyFactory implements ClothFactory {
    // 用被代理类对象进行实例化
    private ClothFactory factory;

    public ProxyFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂准备工作...");

        factory.produceCloth();

        System.out.println("代理工厂结束工作...");
    }
}


/**
 * 被代理类
 */
class NikeFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("Nike 工厂生产...");
    }
}