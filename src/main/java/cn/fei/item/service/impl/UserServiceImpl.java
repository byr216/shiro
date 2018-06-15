package cn.fei.item.service.impl;

import cn.fei.item.domain.entity.User;
import cn.fei.item.mapper.UserMapper;
import cn.fei.item.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liufei
 * @date 2018/6/15 17:19
 */
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
