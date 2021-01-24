package study.qi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import study.qi.realm.CustomerMd5Realm;

import java.util.Arrays;

/**
 * @Description 使用 MD5 + Salt + Hash 散列加密认证
 * @Author qi
 * @Date 2020/8/20 23:07
 * @ClassName CustomerMd5RealmAuthenicatorTest
 **/
public class CustomerMd5RealmAuthenicatorTest {

    public static void main(String[] args) {
        // 创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 实例化自定义 realm 对象
        CustomerMd5Realm realm = new CustomerMd5Realm();

        // 使用凭证匹配器中的 hash 匹配器，并指定算法为 md5
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");

        // 如果使用了散列，还应该告诉凭证器，散列的多少次
        // credentialsMatcher.setHashIterations(1024);

        // 设置 realm 使用 hash 凭证匹配器
        realm.setCredentialsMatcher(credentialsMatcher);

        // 注入 realm
        defaultSecurityManager.setRealm(realm);

        // 将安全管理器注入安全工具
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 通过安全工具类获取 subject
        Subject subject = SecurityUtils.getSubject();

        // 认证
        UsernamePasswordToken token = new UsernamePasswordToken("qi", "123");

        try {
            subject.login(token);
            System.out.println("登录成功！");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误！");
        }


        // 对认证了的用户进行授权
        if (subject.isAuthenticated()) {
            // 1. 基于角色的权限控制
            System.out.println("是否含有 admin 权限：" + subject.hasRole("admin"));

            // 2. 基于多角色权限控制
            System.out.println("是否含有 admin 和 user 权限：" + subject.hasAllRoles(Arrays.asList("admin, user")));

            // 3. 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user", "super"));
            for (Boolean aboolean : booleans) {
                System.out.println("是否有 admin，user，super 中的某一个权限：" + aboolean);
            }

            // 4. 基于权限字符串的访问控制，资源标识符:操作:资源类型
            System.out.println("是否有自定义的 Realm 中的通过权限字符串设置的权限：" + subject.isPermitted("user:*:01"));

            // 5. 分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:02");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            // 6. 同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:*:01", "order:*:02");
            System.out.println("同时具有哪些权限：" + permittedAll);
        }
    }
}