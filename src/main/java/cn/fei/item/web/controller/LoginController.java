package cn.fei.item.web.controller;

import cn.fei.item.assistant.enums.MessageCodeEnum;
import cn.fei.item.domain.response.JsonResponse;
import cn.fei.item.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2018/6/6 16:40
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private EhCacheManager ehCacheManager;

    @RequestMapping("/login")
    public JsonResponse<Void> login() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        JsonResponse result = null;
        if (principal == null) {
            result = new JsonResponse(MessageCodeEnum.PLEASE_LOGIN.getMessageCode(), MessageCodeEnum.PLEASE_LOGIN.getMessage());
        } else {
            result = new JsonResponse(MessageCodeEnum.ALREADY_LOGGED.getMessageCode(), MessageCodeEnum.ALREADY_LOGGED.getMessage());
        }
        return result;
    }

    @RequestMapping("/index")
    public String index() {
        return "登录成功";
    }

    /**
     * 退出登录时清除缓存中用户信息
     */
    @RequestMapping("logout")
    public JsonResponse<Void> logout() {
        System.err.println("--------------退出登录-------");
        userService.clearUserCache();
        SecurityUtils.getSubject().logout();
        return new JsonResponse(MessageCodeEnum.SUCCESS.getMessageCode(), "退出登录");
    }
}
