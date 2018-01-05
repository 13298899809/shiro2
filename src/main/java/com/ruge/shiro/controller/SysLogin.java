package com.ruge.shiro.controller;

import com.ruge.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 武健
 * \* Date: 2018/1/5
 * \* Time: 20:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class SysLogin {

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(User model){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUserName(),model.getPassWord());
            token.setRememberMe(true);
            try {
                subject.login(token);
            }catch (AuthenticationException e){
                e.getMessage();
                System.out.println("登录失败上 ");
            }
        }
        return "redirect:/list.jsp";
    }
  /*  @RequestMapping(value = "/loginOut.do" , method = RequestMethod.POST)
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }*/
}