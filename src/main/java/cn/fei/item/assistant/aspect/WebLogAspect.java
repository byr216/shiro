package cn.fei.item.assistant.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static Logger log = LoggerFactory.getLogger("performance");

    @Pointcut("execution(public * cn.fei.item.web..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        List<Object> args = new ArrayList<>();
        try {
            for (Object o : pjp.getArgs()) {
                if (o instanceof ServletResponse
                        || o instanceof ServletRequest
                        || o instanceof Exception
                        || o instanceof InputStreamSource) {
                    continue;
                }
                args.add(o);
            }
            log.info("========================");
            log.info("URL : " + request.getRequestURL().toString());
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
            log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            log.info("ARGS : " + JSON.toJSONString(args, true));
            Object result = pjp.proceed();
            log.info("OUTPUT : " + JSON.toJSONString(result, true));
            return result;
        } finally {
            long last = System.currentTimeMillis() - start;
            log.info(" execution: " + last + "ms.");
        }
    }
}
