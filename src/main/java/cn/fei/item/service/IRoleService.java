package cn.fei.item.service;

import cn.fei.item.domain.entity.Role;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/18
 */
public interface IRoleService {
    /**
     * 获取所有角色
     */
    public List<Role> selectAll();
}
