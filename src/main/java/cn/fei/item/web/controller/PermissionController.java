package cn.fei.item.web.controller;

import cn.fei.item.assistant.enums.MessageCodeEnum;
import cn.fei.item.domain.response.JsonResponse;
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
    public void reload() {
        permissionService.reload();
    }

    /**
     * 没有权限
     *
     * @return
     */
    @RequestMapping("/noPermission")
    public JsonResponse<Void> noPermission() {
        return new JsonResponse<Void>(MessageCodeEnum.NO_PERMISSION.getMessageCode(), MessageCodeEnum.NO_PERMISSION.getMessage());
    }

    /**
     * 未知错误
     */
    @RequestMapping("unKnowErr")
    public JsonResponse<Void> unKnowErr() {
        return new JsonResponse<Void>(MessageCodeEnum.FAIL.getMessageCode(), MessageCodeEnum.FAIL.getMessage());
    }
}
