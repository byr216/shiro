package cn.fei.shiro.controller;

import cn.fei.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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

    @RequestMapping("ajaxLogin")
    public String ajaxLogin(User user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        SecurityUtils.getSubject().login(token);
        return "success";
    }


    @RequestMapping("/index")
    public String index(){
        return "登录成功";
    }

}
