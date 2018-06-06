package cn.fei.shiro.configuration;

import cn.fei.shiro.security.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liufei
 * @date 2018/6/6 16:16
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        //登录成功后跳转的界面
        factoryBean.setSuccessUrl("/index");
        //自定义拦截器
        Map<String, String> filtersMap = new LinkedHashMap<String, String>();
        filtersMap.put("/logout","logout");
        filtersMap.put("/static/*","anon");
        filtersMap.put("/static/**","anon");
        filtersMap.put("/*","authc");
        filtersMap.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(filtersMap);
        return factoryBean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        return manager;
    }
}
