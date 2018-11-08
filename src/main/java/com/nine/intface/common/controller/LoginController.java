package com.nine.intface.common.controller;

import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.constants.URLConstant;
import com.nine.intface.common.po.User;
import com.nine.intface.common.vo.ResultFactory;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author : Rubi
 * @version : 2018-10-10 10:29
 */
@Slf4j
@Api(value = "/", tags = {"登入登出"})
@RestController
public class LoginController implements Sugar {


    @Autowired
    private HttpServletRequest request;

    @ApiOperation(httpMethod = "GET", value = "登出", tags = {"登入登出"}, notes = "关联shiro,ehcache,session ")
    @RequestMapping("/logout")
    public void logout(HttpServletResponse response) throws Exception {
        String username;
        Subject subject = SecurityUtils.getSubject();
        try {
            username = ((User) subject.getPrincipal()).getUsername();
        } catch (NullPointerException e) {
            username = null;
        }
        subject.logout();
        if (isAjax(request)) {
            sendRedirectUri(URLConstant.INDEX_URL, request, response);
            sendResult(response, ResultFactory.getOKRestResult(username));
        } else {
            WebUtils.issueRedirect(request, response, URLConstant.INDEX_URL);
        }
    }

    //    @RequestMapping(value = "/logina！", method = {RequestMethod.POST, RequestMethod.GET})
//    public Result logsin(@RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam Boolean rememberMe,
//                         HttpServletResponse response) throws Exception {
//        Result result;
//
//
//        //======================清除缓存====================================================\\
////        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
////        MyRealm myRealm = (MyRealm) rsm.getRealms().iterator().next();
////        myRealm.clearCache();
//        //=================================================================================\\
//        Subject current_user = SecurityUtils.getSubject();
//
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
//
//        current_user.login(token);
//
//        if (current_user.isAuthenticated()) {
//            current_user.isPermitted("r");
//            request.getSession().setAttribute("username", username);
//            //current_user.checkPermission("r");
//            //WebUtils.saveRequest(getRequest());
//
//            if (isAjax(request)) {
//                //告诉ajax我是重定向
//                sendRedirectUri(URLConstant.SUCCESS_URL, request, response);
//
//            }
//
//            result = ResultFactory.getOKRestResult(username);
//            //currentUser.getSession().getAttribute("currentUser")
//            //getResponse().sendRedirect("/test_fold/main.html");
//
//        } else {
//            token.clear();
//            result = ResultFactory.getRestResult(-1, "检查用户名及密码,验证未通过");
//
//
//            //   return InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
//        }
//        return result;
//    }
    @ApiOperation(httpMethod = "POST", value = "登入", tags = {"登入登出"}, notes = "关联shiro,ehcache,session ")

    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username", value = "用户名", dataType = "int", paramType = "query", example = "123"),
                    @ApiImplicitParam(name = "password", value = "密码", dataType = "int", paramType = "query", example = "123"),
                    @ApiImplicitParam(name = "rememberMe", value = "记住我", dataType = "boolean", paramType = "query", example = "false")})
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public void login(@RequestParam String username, @RequestParam String password, @RequestParam Boolean rememberMe,
                      HttpServletResponse response) throws Exception {

        // 如果登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        //不可缺少
        log.info("ajaxlogin");
        String exceptionClassName = (String) request.getAttribute(Constant.SHIRO_LOGIN_FAILURE.getName());
        if (exceptionClassName != null) {
            Exception exception = (Exception) Class.forName(exceptionClassName).newInstance();
            throw exception;
        } else {
            AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, request.getRemoteHost());

            if (token == null) {
                String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                        "must be created in order to execute a login attempt.";
                throw new IllegalStateException(msg);
            }
            //       HashMap<String, Object> map = Maps.newHashMap();

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
//                sendRedirectUri(URLConstant.SUCCESS_URL, request, response);
//                map.put("token", subject.getSession().getId());
//                map.put("username", ((DeveloperUser)subject.getPrincipal()).getUsername());


            if (isAjax(request)) {
                sendRedirectUri(URLConstant.SUCCESS_URL, request, response);
                sendResult(response, ResultFactory.getOKRestResult(username));
            } else {
                WebUtils.issueRedirect(request, response, URLConstant.SUCCESS_URL);
            }
            //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
            // 登陆失败还到login页面
            //request.setAttribute("username", request.getParameter("username"));

        }

    }
}
