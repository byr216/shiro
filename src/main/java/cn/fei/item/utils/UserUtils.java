package cn.fei.item.utils;

import cn.fei.item.domain.entity.LoginInfo;
import cn.fei.item.domain.entity.User;
import cn.fei.item.service.IUserService;
import org.apache.shiro.SecurityUtils;

/**
 * @author liufei
 * @date 2018/6/15 15:26
 * <p>
 * 用户信息工具类
 */
public class UserUtils {
    private static String USER_CACHE = "userCache";
    private static String USER_CACHE_KEY = "userCacheKey_";

    /**
     * 获取用户信息
     */
    public static User getUser() {
        LoginInfo loginInfo = getLoginInfo();
        if (loginInfo == null) {
            return null;
        }
        return getUserByUserId(loginInfo.getUserId());
    }

    /**
     * 获取登录信息
     */
    public static LoginInfo getLoginInfo() {
        LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        return loginInfo;
    }

    /**
     * 根据用户id获取用户信息
     *
     * @return
     */
    public static User getUserByUserId(Long id) {
        User user = getUserCache(id);
        if (user == null) {
            //从数据库获取
            IUserService userService = SpringUtils.getBean(IUserService.class);
            user = userService.getUserById(id);
        }
        return user;
    }


    /**
     * 根据用户id获取缓存中用户信息
     *
     * @param id
     * @return
     */
    public static User getUserCache(Long id) {
        //从缓存中获取用户信息
        User user = (User) CachUtils.get(USER_CACHE, USER_CACHE_KEY + id);
        return user;
    }


}
