package cn.fei.item.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author liufei
 * @date 2018/6/17
 */
public class ServletUtils {

    public static ServletResponse getResponse() {
        return ((WebSubject) SecurityUtils.getSubject()).getServletResponse();
    }

    public static ServletRequest getRequest() {
        return ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
    }
}
