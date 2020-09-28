package ks.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/10 18:53
 * @ClassName UserCount
 **/

/**
 * 题目要求：一分钟内完成此题，只能用一行代码
 * 有五个用户，筛选：
 * 1.ID必须是偶数
 * 2.年龄必须大于23岁
 * 3.用户名转为大写字母
 * 4.用户名字母倒着排序
 * 5.只输出一个用户
 */
public class UserCount {

    @Test
    public void userCount() {

        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);

        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5);

        // 链式编程
        userList.stream()
                .filter(u -> {return u.getId()%2 == 0;})
                .filter(u -> {return u.getAge()>23;})
                .map(u -> {return u.getName().toUpperCase();})
                .sorted((uu1, uu2) -> {return uu1.compareTo(uu2);})
                .limit(1)
                .forEach(System.out::println);
    }
}
