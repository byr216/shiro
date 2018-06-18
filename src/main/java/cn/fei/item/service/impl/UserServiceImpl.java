package cn.fei.item.service.impl;

import cn.fei.item.domain.entity.Permission;
import cn.fei.item.domain.entity.Role;
import cn.fei.item.domain.entity.User;
import cn.fei.item.mapper.UserMapper;
import cn.fei.item.service.IPermissionService;
import cn.fei.item.service.IRoleService;
import cn.fei.item.service.IUserService;
import cn.fei.item.shiro.MyShiroRealm;
import cn.fei.item.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liufei
 * @date 2018/6/15 17:19
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private MyShiroRealm realm;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void clearUserCache() {
        UserUtils.clearUserCache();
    }

    @Override
    public void getUserPermissionsAndRoles(User user) {
        List<Permission> permissionList = new ArrayList<>();
        if (user.getIsAdmin() == User.IS_ADMIN) {
            user.setRoleList(roleService.selectAll());
            permissionList = permissionService.selectAll();
        } else {
            user.getRoleList();
            for (Role role : user.getRoleList()) {
                for (Permission permission : role.getPermissionList()) {
                    permissionList.add(permission);
                }
            }
        }
        user.setPermissionList(permissionList);
    }

}
