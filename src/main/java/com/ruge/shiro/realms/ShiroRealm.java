package com.ruge.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 爱丽丝、如歌
 * @Description: TODO
 * @date 2018/1/5 13:14
 */
public class ShiroRealm  extends AuthorizingRealm {
    /**
     * 用于授权的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /**
         * 1.从 PrincipalCollection 中获取登录用户的信息
         */
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        /**
         * 2.利用登录的用户的信息来匹配当前用户的角色或权限(查询数据库)
         */
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(primaryPrincipal)){
            roles.add("admin");
        }
        /**
         * 3.创建 SimpleAuthorizationInfo 并设置roles属性
         */
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        /**
         * 4.返回SimpleAuthenticationInfo 对象
         */
        return simpleAuthorizationInfo;
    }

    /**
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * 1:UsernamePasswordToken 强转成 AuthenticationToken
         */
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        /**
         * 2 从UsernamePasswordToken 中获取userName passWord
         */
        String userName = token.getUsername();
        /**
         * 3 从数据库中获取userName
         */
        System.out.println("======"+userName);
        /**
         * 4 若用户不存在则抛出一个异常
         */
        if ("unknow".equals(userName)){
            throw new RuntimeException("用户不存在");
        }
        /**
         * 5 根据用户情况 来构建AuthenticationToken并返回 通常使用的实现类 SimpleAuthenticationInfo
         * 以下信息是从数据库中获取的
         * 1)princapal 认证的实体信息 可以是userName 也可以是数据表对应的用户的实体类对象
         * 2)credentials:密码
         * 3)realmName:当前realm 对象的name 调用父类的getName() 即可
         * 4)盐值加密   用户名不一样  即便密码一致  但是显示的也不一样
         */
        Object princapal = userName;
        Object credentials = null;
        if("admin".equals(userName)){
            credentials  = "0c295cdc044cb4117d1377bf951d0374";
        }else if("user".equals(userName)){
            credentials  = "7df28ab9a5b62b966ff6d5bd19124135";
        }
        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(userName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(princapal,credentials,salt,realmName);

        return info;
    }
}
