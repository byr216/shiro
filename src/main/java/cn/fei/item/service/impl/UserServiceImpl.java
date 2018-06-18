package cn.fei.item.service.impl;

import cn.fei.item.domain.entity.User;
import cn.fei.item.mapper.UserMapper;
import cn.fei.item.service.IUserService;
import cn.fei.item.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liufei
 * @date 2018/6/15 17:19
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void clearUserCache() {
        UserUtils.clearUserCache();
    }
}
