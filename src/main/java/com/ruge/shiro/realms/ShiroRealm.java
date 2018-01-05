package com.ruge.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 爱丽丝、如歌
 * @Description: TODO
 * @date 2018/1/5 13:14
 */
public class ShiroRealm  extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("密码加密吧");
        return null;
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
         */
        Object princapal = userName;
        Object credentials = "123456";
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(princapal,credentials,realmName);

        return info;
    }
}
