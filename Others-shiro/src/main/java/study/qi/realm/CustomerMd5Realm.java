package study.qi.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Description 自定义的 MD5 Realm
 * @Author qi
 * @Date 2020/8/20 23:03
 * @ClassName CustomerMd5Realm
 **/
public class CustomerMd5Realm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 拿到身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息: " + primaryPrincipal);

        // 根据身份信息（用户名），获取当前用户的角色信息，以及权限信息，假如用户 qi 具有 admin 权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 将数据库中查询角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        // 将数据库中查询的权限信息赋值给对象
        simpleAuthorizationInfo.addStringPermission("user:*:*");

        return simpleAuthorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) token.getPrincipal();

        // 根据用户名查询数据库
        if ("qi".equals(principal)) {
            // 数据库中存储的密码为 123 的 md5 的值为 202cb962ac59075b964b07152d234b70
            // return new SimpleAuthenticationInfo(principal, "202cb962ac59075b964b07152d234b70", this.getName());

            /**
             * 有随机盐的情况
             * 参数1: 数据库名
             * 参数2: 数据库 md5 + salt 之后的密码
             * 参数3: 注册时的随机盐
             * 参数4: realm的名字
             * 这里将盐给定后，会自动带上盐进行 md5 计算
             */
            return new SimpleAuthenticationInfo(principal,
                    "e43a87bc938a5f931a899e76fcf446af",
                    ByteSource.Util.bytes("qiqiqi"),
                    this.getName());
        }
        return null;
    }
}