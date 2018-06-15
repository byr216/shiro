package cn.fei.item.service.impl;

import cn.fei.item.domain.entity.LoginInfo;
import cn.fei.item.mapper.LoginInfoMapper;
import cn.fei.item.service.ILoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/6 16:00
 */
@Service
public class LoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public List<LoginInfo> getUserByUsername(String username) {
        return loginInfoMapper.getByUsername(username);
    }
}
