package cn.fei.item.web.controller;

import cn.fei.item.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2018/6/11 16:06
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 重载权限列表
     */
    @RequestMapping("/reload")
    public String reload(){
        permissionService.reload();
        return "success";
    }
}
