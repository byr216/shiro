package cn.fei.item.web.controller;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

/**
 * @author liufei
 * @date 2018/6/6 16:40
 */
@RestController
public class LoginController {

    @Autowired
    private EhCacheManager ehCacheManager;

    @RequestMapping("/login")
    public String login(){
        return "登录界面";
    }

    @RequestMapping("/index")
    public String index(){
        return "登录成功";
    }

    @RequestMapping("/cach")
    public String getCach(){
        Cache<Object, Object> cache = ehCacheManager.getCache("shiro-activeSessionCache");
        Set<Object> keys = cache.keys();
        Collection<Object> values = cache.values();
        System.out.println(keys);
        return cache.toString();
    }

}
