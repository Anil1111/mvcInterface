package com.lianhe.nine.intface.shiro;



import com.lianhe.nine.intface.po.Permission;
import com.lianhe.nine.intface.po.Role;
import com.lianhe.nine.intface.po.User;
import com.lianhe.nine.intface.service.IUserService;
import com.lianhe.nine.intface.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.Set;

/**
 * @description: ${description}
 * @author: wangyijie
 * @create: 2018-09-12 10:42
 */

/**
 * 而doGetAuthorizationInfo方法是在我们调用
 * SecurityUtils.getSubject().isPermitted（）
 * 这个方法时会调用doGetAuthorizationInfo（），
 * 一而我们的@RequiresPermissions这个注解起始
 * 就是在执行SecurityUtils.getSubject().isPermitted（）。
 * 我们在某个方法上加上@RequiresPermissions这个，
 * 那么我们访问这个方法的时候，就会自动调用
 * SecurityUtils.getSubject().isPermitted（），
 * 从而区调用doGetAuthorizationInfo 匹配
 */
@Cacheable
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private CachingSessionDAO sessionDAO;
    @Autowired
    private IUserService userService;

    /**
     * 登录验证
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * 验证当前登录的Subject
     * 经测试：本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()的时候
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        logger.info("doGetAuthenticationInfo");
        //clearCache();
        /**
         * 因为会调用两边login...
         *  public Subject login(Subject subject, AuthenticationToken token) throws AuthenticationException {
         *         AuthenticationInfo info;
         *         try {
         *             info = authenticate(token);
         *         } catch (AuthenticationException ae) {
         *             try {
         *                 onFailedLogin(token, ae, subject);
         *             } catch (Exception e) {
         *                 if (log.isInfoEnabled()) {
         *                     log.info("onFailedLogin method threw an " +
         *                             "exception.  Logging and propagating original AuthenticationException.", e);
         *                 }
         *             }
         *             throw ae; //propagate
         *         }
         */


        //logger.info(ReflectionToStringBuilder.toString(authcToken));
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // logger.info(authcToken.getPrincipal());//身份

        User user = null;
        try {
            user = userService.getByUsername(token.getUsername());
        } catch (Exception e) {
            logger.error("exception:{} info: ", e.getClass().getName(), e);
        }
        if (null == user) {
            return null;
        }

//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//
//        for(Session session:sessions){
//
//            if(token.getUsername().equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))){
//
//                session.setTimeout(0);//设置session立即失效，即将其踢出系统
//                logger.info("用户{}已登录，踢出之前session",token.getUsername());
//                break;
//
//            }
//
//        }


        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());


           /* logger.info("1");
            PrincipalCollection principalCollection= SecurityUtils.getSubject().getPrincipals();
            logger.info("1");
            System.out.println(ReflectionToStringBuilder.toString(principalCollection));
            doGetAuthorizationInfo(principalCollection);
            logger.info("1");*/
        //  System.out.println(ReflectionToStringBuilder.toString(authcInfo));


        // sessionDAO.create(session1);
        // System.out.println(sessionManager.getSessionDAO().getActiveSessions());

        return authcInfo;
    }

    /**
     * 获取授权信息
     * 为当前登录的Subject授予角色和权限
     * -------------------------------------------------------------------------------------------
     * 认证回调函数,登录时调用.
     * 经测试：本例中该方法的调用时机为需授权资源被访问时
     * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持，则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时，先去访问Spring3.1提供的缓存，而不使用Shiro提供的AuthorizationCache
     * -----------------------------------------------------------------------------------------------
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo");
        //获取当前登录的用户名
//        getAvailablePrincipal(principals)
        String username = ((User) principals.getPrimaryPrincipal()).getUsername();
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

        try {
            //实体类User中包含有用户角色的实体类信息
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            this.addRole(username, simpleAuthorInfo);
            this.addPermission(username, simpleAuthorInfo);
            //throw new NullPointerException();
            return simpleAuthorInfo;
        } catch (Exception e) {
            logger.warn("{} has an Exception {}", getName(), e);
        }

        return null;
    }


    //此处无需比对，比对的逻辑Shiro会做，我们只需返回一个和令牌相关的正确的验证信息
    //说白了就是第一个参数填登录用户名，第二个参数填合法的登录密码（可以是从数据库中取到的，本例中为了演示就硬编码了）
    //这样一来，在随后的登录页面上就只有这里指定的用户和密码才能通过验证
//        if ("jadyer".equals(token.getUsername())) {
//            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("jadyer", "jadyer", this.getName());
//            this.setAuthenticationSession("jadyer");
//            return authcInfo;
//        }
//        if ("xuanyu".equals(token.getUsername())) {
//            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("xuanyu", "xuanyu", this.getName());
//            this.setAuthenticationSession("xuanyu");
//            return authcInfo;
//        }
    //没有返回登录用户名对应的SimpleAuthenticationInfo对象时，就会在LoginController中抛出UnknownAccountException异常


    private void addRole(String username, SimpleAuthorizationInfo info) throws Exception {
        Set<Role> roles = userService.getRoles(username);
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                if (role != null && role.getRole_name() != "") {
                    info.addRole(role.getRole_name());
                }
            }
        }

    }

    private void addPermission(String username, SimpleAuthorizationInfo info) throws Exception {
        Set<Permission> permissions = userService.getPermissions(username);
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                if (permission != null && permission.getPermission_name() != "") {
                    info.addStringPermission(permission.getPermission_name());//添加权限
                }
            }
        }

        //return info;
    }


    public void clearCache() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        this.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
        /*PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);*/
        //super.doClearCache(SecurityUtils.getSubject().getPrincipals());
        //this.clear
    }

}
