package cn.fei.shiro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author liufei
 * @date 2018/6/7 17:15
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {


        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("---------------------");

        return super.onLoginSuccess(token, subject, request, response);
    }
}
