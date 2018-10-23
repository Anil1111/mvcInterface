package com.nine.intface.common.config;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.nine.intface.common.constants.URLConstant;
import com.nine.intface.common.filter.LoginFilter;
import com.nine.intface.common.filter.RubiPermissionFilter;
import com.nine.intface.common.filter.RubiRoleFilter;
import com.nine.intface.common.filter.RubiUserFilter;
import com.nine.intface.common.po.UrlFilter;
import com.nine.intface.common.service.IUrlFilterService;
import com.nine.intface.common.shiro.MyRealm;
import com.nine.intface.common.shiro.RetryLimitHashedCredentialsMatcher;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-09 17:21
 */

@Configuration
//@DependsOn({"shiroUrlFilterService"})
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public PreServiceConfig getService(){
        return new PreServiceConfig();
    }

   /* @Autowired
    private IConfigService configService;*/
//    @Bean("configService")
//    public IConfigService getShiroUrlFilterService(ShiroUrlFilterMapper shiroUrlFilterMapper){
//        return new ShiroServiceImpl(shiroUrlFilterMapper);
//    }

//    @Bean("configService")
//    public IConfigService getShiroUrlFilterService(){
//        return new ShiroServiceImpl();
//    }
//    @Autowired
//    private IConfigService configService;

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        logger.info("----------------ConfigInit:sessionIdCookie");
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(180000);
        return sessionIdCookie;
    }

//    @Bean(name = "sessionDAO")
//    public SessionDAO sessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator) {
//        logger.info("----------------ConfigInit:sessionDAO");
//        RubiSessionDao sessionDAO = new RubiSessionDao();
//        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
//        return sessionDAO;
//    }
    //EnterpriseCacheSessionDAO
    @Bean(name = "sessionDAO")
    public CachingSessionDAO sessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator, EhCacheManager shiroCacheManager) {
        logger.info("----------------ConfigInit:sessionDAO");
        CachingSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setCacheManager(shiroCacheManager);
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        logger.info("----------------ConfigInit:sessionIdGenerator");
        return new JavaUuidSessionIdGenerator();
    }

    //    @Lazy
//    @Bean(name = "sessionValidationScheduler")
//    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler(ValidatingSessionManager sessionManager) {
//        logger.info("----------------ConfigInit:sessionValidationScheduler");
//        ExecutorServiceSessionValidationScheduler serviceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
//        serviceSessionValidationScheduler.setInterval(3000000);
//        serviceSessionValidationScheduler.setSessionManager(sessionManager);
//        return serviceSessionValidationScheduler;
//    }
    @Lazy
    @Bean(name = "sessionValidationScheduler")
    public SessionValidationScheduler sessionValidationScheduler(ValidatingSessionManager sessionManager) {
        logger.info("----------------ConfigInit:sessionValidationScheduler");
        QuartzSessionValidationScheduler serviceSessionValidationScheduler = new QuartzSessionValidationScheduler();
        serviceSessionValidationScheduler.setSessionValidationInterval(3000000);
        serviceSessionValidationScheduler.setSessionManager(sessionManager);
        return serviceSessionValidationScheduler;
    }

    @Lazy
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(
            SessionValidationScheduler sessionValidationScheduler,
            SessionDAO sessionDAO,
            SimpleCookie sessionIdCookie) {
        logger.info("----------------ConfigInit:sessionManager");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        RubiSessionManager
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(1500000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    @Bean(name = "methodInvoking")
    public MethodInvokingFactoryBean methodInvoking(SecurityManager securityManager) {
        logger.info("----------------ConfigInit:methodInvoking");
        MethodInvokingFactoryBean methodInvoking = new MethodInvokingFactoryBean();
        methodInvoking.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvoking.setArguments(securityManager);
        return methodInvoking;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        logger.info("----------------ConfigInit:rememberMeManager");
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        logger.info("----------------ConfigInit:rememberMeCookie");
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setMaxAge(86400);
        return rememberMeCookie;
    }

    //缓存配置
    @Bean(name = "ehcacheManagerFactory")
    public EhCacheManagerFactoryBean ehCacheManager() {
        logger.info("----------------ConfigInit:ehcacheManagerFactory");
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
    //spring 封装ehcache缓存管理器
    @Bean(name = "cacheManager")
    public EhCacheCacheManager cacheManager(CacheManager ehcacheManagerFactory) {
        logger.info("----------------ConfigInit:cacheManager");
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehcacheManagerFactory);
        return cacheManager;
    }
    //shiro封装cacheManager
    @Bean(name = "shiroCacheManager")
    public EhCacheManager shiroCacheManager(CacheManager ehcacheManagerFactory) {
        logger.info("----------------ConfigInit:shiroCacheManager");
        EhCacheManager shiroCacheManager = new EhCacheManager();

        shiroCacheManager.setCacheManager(ehcacheManagerFactory);
        return shiroCacheManager;
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher credentialsMatcher(EhCacheManager shiroCacheManager) {
        logger.info("----------------ConfigInit:credentialsMatcher");
        RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher(shiroCacheManager);
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(9);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }
//    <!-- 将shiro的缓存管理器，注入到安全管理器中
//    要想在Realm中将查询的数据放置到缓存中，
//    需要在Realm之前创建动态代理对象，通过缓存代理控制Realm
//	 -->

    @Bean(name = "myRealm")
    public MyRealm myRealm(CredentialsMatcher credentialsMatcher) {
        logger.info("----------------ConfigInit:myRealm");
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        myRealm.setAuthenticationCacheName("authenticationCache");
        myRealm.setAuthorizationCacheName("authorizationCache");

        myRealm.setCachingEnabled(true);
        myRealm.setAuthenticationCachingEnabled(true);
        myRealm.setAuthorizationCachingEnabled(true);
        return myRealm;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(MyRealm myRealm, EhCacheManager shiroCacheManager) {
        logger.info("----------------ConfigInit:securityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        securityManager.setCacheManager(shiroCacheManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(
            SecurityManager securityManager,
            IUrlFilterService shiroService
    ) throws Exception {

        logger.info("----------------ConfigInit:shiroFilter");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put("user", new RubiUserFilter());
        filterMap.put("authc", new LoginFilter());
        filterMap.put("roles", new RubiRoleFilter());
        filterMap.put("perms", new RubiPermissionFilter());
        List<UrlFilter> list = shiroService.getAllUrlFilter();
        Map<String, String> urlMap = Maps.newLinkedHashMap();
        for (UrlFilter urlAndFilter : list) {
            urlMap.put(urlAndFilter.getUri(), urlAndFilter.getFilter());
            logger.info("----------------urlAndFilter:{} : {}", Strings.padEnd(urlAndFilter.getUri(), 40, ' '), urlAndFilter.getFilter());
        }


        shiroFilterFactoryBean.setLoginUrl("/login");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/");  //权限不够会弹到这li
        shiroFilterFactoryBean.setSuccessUrl(URLConstant.SUCCESS_URL);
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlMap);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        logger.info("----------------ConfigInit:authorizationAttributeSourceAdvisor");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        logger.info("----------------ConfigInit:lifecycleBeanPostProcessor");
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        logger.info("----------------ConfigInit:defaultAdvisorAutoProxyCreator");
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }



}
