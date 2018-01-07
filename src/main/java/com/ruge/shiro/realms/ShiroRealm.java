package com.ruge.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
