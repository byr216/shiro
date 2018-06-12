package cn.fei.item.web.controller;

import cn.fei.item.annotation.MyPermissionAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    @MyPermissionAnnotation(name = "用戶列表")
    public String list(){
        return "获取用户列表成功";
    }
}
