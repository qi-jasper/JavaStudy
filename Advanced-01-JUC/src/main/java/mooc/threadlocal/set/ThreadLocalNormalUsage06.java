package mooc.threadlocal.set;

/**
 * @Description 演示ThreadLocal用法2：避免传递参数的麻烦
 * @Author qi
 * @Date 2020/6/23 11:39
 * @ClassName ThreadLocalNormalUsage06
 **/
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        // 期望：Service1设置对象后，Service2和Service3能够直接拿到
        new Service1().process("");
    }
}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}

// 通过ThreadLocal定义对象持有者
class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

// 生成User对象
class Service1 {
    public void process(String name) {
        User user = new User("qi哥");
        // 通过set()方法，将对象放入ThreadLocal中
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        // 通过get()方法直接读取
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        // 通过get()方法直接读取
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }
}