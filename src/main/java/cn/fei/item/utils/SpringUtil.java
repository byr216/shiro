package cn.fei.item.utils;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class SpringUtil {

    private static RequestMappingHandlerMapping rmhm;

    public static RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        if(rmhm == null){
        }
        return rmhm;
    }

}
