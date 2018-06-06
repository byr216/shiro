package cn.fei.shiro.service.impl;

import cn.fei.shiro.domain.User;
import cn.fei.shiro.domain.UserExample;
import cn.fei.shiro.mapper.UserMapper;
import cn.fei.shiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/6 16:00
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(example);
    }
}
