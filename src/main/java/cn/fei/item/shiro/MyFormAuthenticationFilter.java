package cn.fei.item.shiro;

import cn.fei.item.assistant.enums.MessageCodeEnum;
import cn.fei.item.assistant.exception.JsonException;
import cn.fei.item.domain.entity.User;
import cn.fei.item.domain.response.JsonResponse;
import cn.fei.item.service.IUserService;
import cn.fei.item.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author liufei
 * @date 2018/6/7 17:15
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Autowired
    private IUserService userService;

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("登录失败");

        String message = e.getClass().getSimpleName();
        String msgCode = "";
        String msg = "";
        if ("IncorrectCredentialsException".equals(message)) {
            msgCode = MessageCodeEnum.INCORRECT_CREDENTIALS.getMessageCode();
            msg = MessageCodeEnum.INCORRECT_CREDENTIALS.getMessage();
        } else if ("UnknownAccountException".equals(message)) {
            msgCode = MessageCodeEnum.UNKNOWN_ACCOUNT.getMessageCode();
            msg = MessageCodeEnum.UNKNOWN_ACCOUNT.getMessage();
        } else if ("LockedAccountException".equals(message)) {
            msgCode = MessageCodeEnum.LOCKED_ACCOUNT.getMessageCode();
            msg = MessageCodeEnum.LOCKED_ACCOUNT.getMessage();
        } else if ("AuthenticationException".equals(message)) {
            msgCode = MessageCodeEnum.AUTHENTICATION.getMessageCode();
            msg = MessageCodeEnum.AUTHENTICATION.getMessage();
        } else {
            msgCode = MessageCodeEnum.FAIL.getMessageCode();
            msg = MessageCodeEnum.FAIL.getMessage();
        }
        throw new JsonException(msgCode, msg);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("--------登录成功-------------");
        //获取用户信息
        User user = UserUtils.getUser();
        //获取用户权限
        userService.getUserPermissionsAndRoles(user);
        JsonResponse<User> jsonResponse = new JsonResponse<>(MessageCodeEnum.SUCCESS.getMessageCode(), "登录成功", user);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(jsonResponse));
        out.flush();
        out.close();
        return false;
    }

    /**
     * 解决登录成功后进入登录前访问界面问题，设置后每次登录后默认都会强制进入设置好的主界面
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
    }
}
