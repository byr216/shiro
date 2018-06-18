package cn.fei.item.service.impl;

import cn.fei.item.assistant.annotation.MyPermissionAnnotation;
import cn.fei.item.domain.entity.Permission;
import cn.fei.item.mapper.PermissionMapper;
import cn.fei.item.service.IPermissionService;
import cn.fei.item.utils.SpringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.*;

/**
 * @author liufei
 * @date 2018/6/11 16:09
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void reload() {
        /**
         * 增加系统中新配置的权限
         * 删除系统中删除的权限
         */
        //获取数据库中所有权限
        List<Permission> permissions = permissionMapper.selectAll();
        //用于筛选系统中已删除的权限
        Map<String, Long> map = new HashMap<>();
        for (Permission permission : permissions) {
            map.put(permission.getPermissionValue(), permission.getId());
        }
        Set<String> permissionValueSet = map.keySet();
        RequestMappingInfoHandlerMapping rmfm = SpringUtils.getBean(RequestMappingInfoHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmfm.getHandlerMethods();
        Permission permission = null;
        Set<String> sysPermissions = new HashSet<>();
        for (HandlerMethod method : handlerMethods.values()) {
            if (method.hasMethodAnnotation(RequiresPermissions.class)) {
                //获取方法上权限
                String value = method.getMethodAnnotation(RequiresPermissions.class).value()[0];
                sysPermissions.add(value);
                //判断系统中是否已经有此权限
                if (!permissionValueSet.contains(value)) {
                    permission = new Permission();
                    permission.setPermissionValue(value);
                    if (method.hasMethodAnnotation(MyPermissionAnnotation.class)) {
                        permission.setPermissionName(method.getMethodAnnotation(MyPermissionAnnotation.class).name());
                    }
                    permissionMapper.insert(permission);
                }
            }
        }
        //删除系统中不存在的权限(当系统删除了某个权限时,加载权限后删除数据库中原本配置的该权限)
        for (String str : permissionValueSet) {
            if (!sysPermissions.contains(str)) {
                permissionMapper.deleteByPrimaryKey(map.get(str));
            }
        }

    }
}
