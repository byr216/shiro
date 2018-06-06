package cn.fei.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liufei
 * @date 2018/6/6 11:36
 */
@RequestMapping("user")
@Controller
public class UserController {

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        System.out.println("test");
    }
}
