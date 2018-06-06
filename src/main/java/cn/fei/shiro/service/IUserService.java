package cn.fei.shiro.service;

import cn.fei.shiro.domain.User;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/6 15:59
 */
public interface IUserService {

    /**
     * 根据用户名获取用户
     */
    List<User> getUserByUsername(String username);
}
