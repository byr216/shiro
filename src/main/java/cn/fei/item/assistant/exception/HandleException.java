package cn.fei.item.assistant.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * /**
 *
 * @author liufei
 * @date 2018/6/16 21:27
 * <p>
 * 用于全局统一异常处理
 */
@Slf4j
@Component
public class HandleException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        printInfo(request, response, o, e);
        try {
            if (e instanceof UnauthorizedException) {
                //重定向到没有权限接口
                request.getRequestDispatcher("/permission/noPermission").forward(request, response);

            } else {
                //未知错误
                request.getRequestDispatcher("/permission/unKnowErr").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 输出异常信息
     *
     * @param request
     * @param response
     * @param handler
     * @param e
     */
    private void printInfo(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        String url = request.getRequestURI();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        log.error("--------------------------异常信息打印----------------------------:" + dateFormat.format(new Date()));
        String str = "";
        if (handler != null) {
            str = handler.toString().trim();
            //去掉修饰符和参数
            int index = str.indexOf(" ");
            if (index != -1) str = str.substring(index, str.length() - 1).trim();
            index = str.indexOf(" ");
            if (index != -1) str = str.substring(index + 1, str.length() - 1).trim();
            index = str.indexOf("(");
            if (index != -1) str = str.substring(0, index).trim();
        }
        log.error("异常信息:" + e.getMessage());
        log.error("url: " + url);
        log.error("method: " + request.getMethod());
        log.error("调用方法：" + str);
        log.error("参数：");
        Map parameterMap = request.getParameterMap();
        for (Object key : parameterMap.keySet()) {
            String name = (String) key;
            String value = request.getParameter(name);
            log.error(name + " : " + value);
        }

        log.error("------------------------------------------------------: " + dateFormat.format(new Date()));
    }
}
