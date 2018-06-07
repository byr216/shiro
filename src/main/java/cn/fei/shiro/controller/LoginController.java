package cn.fei.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2018/6/6 16:40
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "登录界面";
    }

    @RequestMapping("/")
    public void login2(){
        System.out.println("//////");
        this.login();
    }


    @RequestMapping("/index")
    public String index(){
        return "登录成功";
    }

}
