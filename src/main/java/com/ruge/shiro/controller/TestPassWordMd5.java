package com.ruge.shiro.controller;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 武健
 * \* Date: 2018/1/5
 * \* Time: 21:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TestPassWordMd5 {
    public static void main(String[] args) {
        String method = "MD5";
        String passWord = "123456";
        String salt = "user";
        int num = 9527;
       Object result =  new SimpleHash(method,passWord,salt,num);
        System.out.println(result);
    }
}