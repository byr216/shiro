package cn.fei.shiro.configuration;

import cn.fei.shiro.security.MyFormAuthenticationFilter;
import cn.fei.shiro.security.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liufei
 * @date 2018/6/6 16:16
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        //登录成功后跳转的界面
        factoryBean.setSuccessUrl("/index");
        //自定义拦截器
        Map<String,Filter> filterMap = new HashMap<>();
        Map<String, String> map = new LinkedHashMap<String, String>();
        filterMap.put("authc",getMyFormAuthenticationFilter());
        map.put("/ajaxLogin","anon");
        map.put("/logout","logout");
        map.put("/static/*","anon");
        map.put("/static/**","anon");
        map.put("/*","authc");
        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        factoryBean.setFilters(filterMap);
        return factoryBean;
    }

    @Bean(name="securityManager")
    public SecurityManager GetSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        return manager;
    }

    @Bean(name = "myFormAuthenticationFilter")
    public MyFormAuthenticationFilter getMyFormAuthenticationFilter(){
        return new MyFormAuthenticationFilter();
    }

    /**
     * 开启AOP权限注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
