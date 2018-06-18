package cn.fei.item.service.impl;

import cn.fei.item.domain.entity.Role;
import cn.fei.item.mapper.RoleMapper;
import cn.fei.item.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/18
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
}
