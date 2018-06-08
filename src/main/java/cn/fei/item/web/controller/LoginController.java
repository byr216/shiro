package cn.fei.item.web.controller;

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

    @RequestMapping("/index")
    public String index(){
        return "登录成功";
    }

}
