package cn.fei.item.mapper;

import cn.fei.item.domain.entity.Permission;
import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    /**
     * 获取系统中所有的权限值集合
     * @return
     */
    Set<String> getPermissionValueSet();
}