package cn.fei.item.configuration;

import cn.fei.item.shiro.MyFormAuthenticationFilter;
import cn.fei.item.shiro.MySessionDao;
import cn.fei.item.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
    @Value("${shiro.credentials.algorithm}")
    private String algoritmh;
    @Value("${shiro.credentials.iterations}")
    private int iterations;


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        //登录成功后跳转的界面
        factoryBean.setSuccessUrl("/index");
        //未授权跳转
        factoryBean.setUnauthorizedUrl("/user/noPermission");
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/logout", "logout");
        //如果想要自定义退出时操作流程,需要把logout设置成anon
        map.put("/logout", "anon");
        map.put("/static/*", "anon");
        map.put("/static/**", "anon");
        map.put("/**/favicon.ico", "anon");
        map.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        //自定义拦截器
        Map<String, Filter> filterMap = new HashMap<>();

        filterMap.put("authc", getMyFormAuthenticationFilter());
        factoryBean.setFilters(filterMap);
        return factoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager GetSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm,
                                              @Qualifier("sessionManager") SessionManager sessionManager) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        myShiroRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        manager.setRealm(myShiroRealm);
        // 配置sessionManager
        manager.setSessionManager(sessionManager);
        manager.setCacheManager(getEhCacheManager());
        manager.setRememberMeManager(getRememberMeManager());
        return manager;
    }

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return ehCacheManager;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        System.err.println("----------------加载ehcache---------------------");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(
                new ClassPathResource("cache/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean(name = "sessionManager")
    public SessionManager getSessionManager(@Qualifier("sessionDao") SessionDAO sessionDAO,
                                            @Value("${shiro.globalSessionTimeout}") Long timeOut) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(timeOut);
        sessionManager.setSessionIdCookie(getSimpleCookie());
        return sessionManager;
    }

    @Bean(name = "sessionDao")
    public SessionDAO getSessionDao(@Value("${shiro.sessionCachName}") String sessionCachName) {
        MySessionDao sessionDAO = new MySessionDao();
        sessionDAO.setActiveSessionsCacheName(sessionCachName);
        return sessionDAO;
    }

    /**
     * 指定本系统SESSIONID, 默认为: JSESSIONID 问题: 与SERVLET容器名冲突, 如JETTY, TOMCAT 等默认JSESSIONID,
     * 当跳出SHIRO SERVLET时如ERROR-PAGE容器会为JSESSIONID重新分配值导致登录会话丢失!
     *
     * @return
     */
    public SimpleCookie getSimpleCookie() {
        SimpleCookie cookie = new SimpleCookie("jsid");
        cookie.setHttpOnly(true);
        return cookie;
    }


    @Bean(name = "myFormAuthenticationFilter")
    public MyFormAuthenticationFilter getMyFormAuthenticationFilter() {
        return new MyFormAuthenticationFilter();
    }

    @Bean("myCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hdm = new HashedCredentialsMatcher();
        hdm.setHashAlgorithmName(algoritmh);
        hdm.setHashIterations(iterations);
        return hdm;
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

    @Bean
    public RememberMeManager getRememberMeManager() {
        RememberMeManager rememberMeManager = new CookieRememberMeManager();
        return rememberMeManager;
    }
}
