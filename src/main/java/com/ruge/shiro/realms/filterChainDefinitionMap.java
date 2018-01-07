package com.ruge.shiro.realms;

import java.util.LinkedHashMap;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 武健
 * \* Date: 2018/1/7
 * \* Time: 16:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class filterChainDefinitionMap {

    public LinkedHashMap<String,String> init (){
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("/login.jsp","anon");
        linkedHashMap.put("/login.do","anon");
        linkedHashMap.put("/loginOut.do","logout");
        linkedHashMap.put("/user.jsp","roles[user]");
        linkedHashMap.put("/admin.jsp","roles[admin]");
        linkedHashMap.put("/**","authc");
        System.out.println("来一波Map 吧");

        return linkedHashMap;
    }
}