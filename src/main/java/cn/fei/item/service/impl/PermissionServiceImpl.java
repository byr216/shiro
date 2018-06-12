package cn.fei.item.service.impl;

import cn.fei.item.mapper.PermissionMapper;
import cn.fei.item.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ApplicationContext act;


    @Override
    public void reload() {
        //获取系统中所有controller类
        Map<String, Object> beans = act.getBeansWithAnnotation(RestController.class);
        //遍历所有bean，获取所有bean中被@MyPermissionAnnotation注解标注的方法
        System.out.println("aaaaaaaaaaaa");
    }
}
