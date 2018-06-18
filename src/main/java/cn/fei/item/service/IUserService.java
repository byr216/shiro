package cn.fei.item.service;

import cn.fei.item.domain.entity.User;

/**
 * @author liufei
 * @date 2018/6/15 17:18
 */
public interface IUserService {
    /**
     * 根据用户id获取用户
     */
    public User getUserById(Long id);

    /**
     * 清空用户缓存信息
     */
    void clearUserCache();
}
