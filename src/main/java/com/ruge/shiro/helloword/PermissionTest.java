package com.ruge.shiro.helloword;

import org.apache.shiro.subject.Subject;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 爱丽丝、如歌
 * @Description: TODO
 * @date 2018/1/2 13:03
 */
public class PermissionTest {
    public static void main(String[] args) {
      Subject subject =  ShiroUtil.login("classpath:shiro_permission.ini","ruge","ruge");
        System.out.println(subject.isPermitted("user:select"));

        boolean [] booleans = subject.isPermitted("user:select","user:add","user:update","user:delete");
        for (int i = 0; i <booleans.length ; i++) {
            System.out.println((i+1)+"==="+booleans[i]);
        }
    }
}
