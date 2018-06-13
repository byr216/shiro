package cn.fei.item.service.impl;

import cn.fei.item.mapper.PermissionMapper;
import cn.fei.item.service.IPermissionService;
import cn.fei.item.utils.SpringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.Map;

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
        RequestMappingInfoHandlerMapping rmfm = SpringUtil.getBean(RequestMappingInfoHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmfm.getHandlerMethods();
        for (HandlerMethod method : handlerMethods.values()) {
            if (method.hasMethodAnnotation(RequiresPermissions.class)) {
                System.out.println(method);
            }
        }
    }
}
