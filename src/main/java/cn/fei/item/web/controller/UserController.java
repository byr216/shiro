package cn.fei.item.web.controller;

import cn.fei.item.assistant.annotation.MyPermissionAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2018/6/6 11:36
 */
@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        System.out.println("test");
    }

    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    @MyPermissionAnnotation(name = "用戶列表")
    public String list() {
        return "获取用户列表成功";
    }

}
