package cn.fei.item.service.impl;

import cn.fei.item.annotation.MyPermissionAnnotation;
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

import java.util.Map;
import java.util.Set;

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
        //获取数据库中所有权限
        Set<String> permissionSet =  permissionMapper.getPermissionValueSet();
        RequestMappingInfoHandlerMapping rmfm = SpringUtils.getBean(RequestMappingInfoHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmfm.getHandlerMethods();
        Permission permission = null;
        for (HandlerMethod method : handlerMethods.values()) {
            if (method.hasMethodAnnotation(RequiresPermissions.class)) {
                //获取方法上权限
                String value = method.getMethodAnnotation(RequiresPermissions.class).value()[0];
                //判断系统中是否已经有此权限
                if(!permissionSet.contains(value)){
                    permission = new Permission();
                    permission.setPermissionValue(value);
                    if(method.hasMethodAnnotation(MyPermissionAnnotation.class)){
                        permission.setPermissionName(method.getMethodAnnotation(MyPermissionAnnotation.class).name());
                    }
                    permissionMapper.insert(permission);
                }
            }
        }
    }
}
