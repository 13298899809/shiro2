package com.ruge.shiro.helloword;

import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 爱丽丝、如歌
 * @Description: TODO
 * @date 2018/1/2 11:39
 */
public class RoleTest {

    public static void main(String[] args) {
        Subject  subject = ShiroUtil.login("classpath:shiro_role.ini","ruge","ruge");
        System.out.println(subject.hasRole("role2"));
        System.out.println(subject.hasRole("role2")==true?"有":"没有");
        List list = new ArrayList();
        list.add("role1");
        list.add("role2");
        list.add("role3");
        boolean[] booleans = subject.hasRoles(list);
        for (int i = 0; i <booleans.length ; i++) {
            System.out.println(booleans[i]);
        }
    }

}
