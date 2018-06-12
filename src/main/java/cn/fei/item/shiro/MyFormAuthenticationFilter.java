package cn.fei.item.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liufei
 * @date 2018/6/7 17:15
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("登录失败");

        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            } else if ("AuthenticationException".equals(message)) {
                out.println("{\"success\":false,\"msg\":\"账号被锁定\"}");
            } else {
                out.println("{\"success\":false,\"msg\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("--------登录成功-------------");

        return super.onLoginSuccess(token, subject, request, response);
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
