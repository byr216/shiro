package cn.fei.item.service;

import cn.fei.item.domain.entity.Permission;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/11 16:09
 */
public interface IPermissionService {
    /**
     * 重载权限
     */
    public void reload();

    /**
     * 获取所有权限
     * @return
     */
    List<Permission> selectAll();
}
