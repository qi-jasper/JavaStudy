package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 动态代理举例
 * @Author qi
 * @Date 2020/9/30 9:55
 **/

public class DynamicProxy {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();

        // proxyInstance 代理类的对象
        Human proxyInstance = (Human) DynamicProxyFactory.getProxyInstance(superMan);

        // 通过代理类对象调用方法时，会自动调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("辣椒");
    }
}

interface Human {

    String getBelief();
    void eat(String food);
}

/**
 * 被代理类
 */
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "this is a superman";
    }

    @Override
    public void eat(String food) {
        System.out.println("I like eating" + food);
    }
}

class DynamicProxyFactory {

    /**
     * 创建的被代理类的对象
     * @param obj 被代理对象
     * @return 返回一个代理类对象，以此来解决问题一
     */
    public static Object getProxyInstance(Object obj) {

        MyHandler myHandler = new MyHandler();
        myHandler.bind(obj);

        /**
         * 创建代理类对象
         * 参数1. 获取代理类的类加载器
         * 参数2. 代理类和被代理类实现同样的接口，传入接口
         * 参数3. 需要传入一个 InvocationHandler，这里需要自己去实现一个，具体实现见 MyHandler
         */
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myHandler);
    }
}

/**
 * 参数3
 */
class MyHandler implements InvocationHandler {

    // 声明被代理类，需要使用被代理类的对象进行赋值
    private Object obj;

    // 给 obj 赋值
    public void bind(Object obj) {
        this.obj = obj;
    }

    /**
     * 当通过代理类的对象调用某个方法时，就会自动调用这个 invoke() 方法
     * 将被代理类要执行的某个方法的功能就声明在 invoke() 中，以此解决问题2
     * @param proxy 代理类对象
     * @param method 代理类对象所调用的方法
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * method.invoke():即为代理类对象调用的方法
         * 此方法也就作为了被代理类调用的方法，传入被代理对象 obj 和 方法参数 args
         */
        Object returnVal = method.invoke(obj, args);

        // 上述方法的返回值就作为当前类中的 invoke() 方法的返回值
        return returnVal;
    }
}