package study.qi.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description 自定义 Realm
 * @Author qi
 * @Date 2020/8/18 17:17
 * @ClassName CustomerRealm
 **/

public class CustomerRealm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在 token 中获取用户名
        String principal = (String) token.getPrincipal();
        System.out.println(principal);

        // 根据身份信息使用 jdbc/mybatis 查询相关数据库
        if ("qi".equals(principal)) {
            /**
             * 参数1: 返回数据库中正确的用户名
             * 参数2: 返回数据库中正确的密码
             * 参数3: 提供当前 realm 的名字，这个参数不重要
             */
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, "123", this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
