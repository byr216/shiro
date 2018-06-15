package cn.fei.item.mapper;

import cn.fei.item.domain.entity.RolePermission;
import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);
}