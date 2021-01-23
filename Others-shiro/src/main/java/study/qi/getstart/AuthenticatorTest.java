package study.qi.getstart;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Description shiro 入门
 * @Author qi
 * @Date 2020/8/17 10:14
 * @ClassName AuthenticatorTest
 **/
public class AuthenticatorTest {

    public static void main(String[] args) {
        // 1. 创建安全管理对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2. 给安全管理器设置 realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        // 3. SecurityUtils 给安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4. 关键对象：subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 5. 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("qi", "123");

        try {
            System.out.println("认证状态：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败！用户名不存在!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败！密码错误！");
        }

    }
}