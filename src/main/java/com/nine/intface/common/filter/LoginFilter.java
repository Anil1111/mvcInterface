package com.nine.intface.common.filter;//package com.noob.filter;

import com.nine.intface.common.constants.URLConstant;
import com.nine.intface.common.controller.Sugar;
import com.nine.intface.common.po.User;
import com.nine.intface.common.vo.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description MyFormAuthenticationFilter 自定义session失效跳转页面
 * @Date 2017年9月18日 下午4:48:03
 */
@Slf4j
public class LoginFilter extends FormAuthenticationFilter implements Sugar {

    // session获取
    //CustomShiroSessionDAO customShiroSessionDAO;


    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(isAjax(req)){
            sendResult(response,ResultFactory.getOKRestResult(((User) subject.getPrincipal()).getUsername()));
        }else {
            RedirectToSuccess(request,response);
        }
        /*HashMap<String, Object> map = Maps.newHashMap();
        map.put("token", subject.getSession().getId());
        map.put("username", );
        sendJson(resp, ResultFactory.getOKRestResult(map));*/
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isAjax(req)) {
            setFailureAttribute(request, e);
        } else {
            try {
                setFailureAttribute(request, e);
                saveRequestAndRedirectToLogin(request,response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
        /*如果满足
         *（1）.当前的subject是被认证过的。
         *（2）.用户请求的不是登录页面，但是在定义该过滤器时，使用了PERMISSIVE=”permissive”参数。只要满足两个条件的一个即可允许操作
         *
         *  SecurityUtils.getSubject().isAuthenticated() ||
         * (!isLoginRequest(request, response) && isPermissive(mappedValue));
         * **/
    }


    @Override //当未登录请求被拦截，会调用FormAuthenticationFilter.onAccessDenied():
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (isLoginRequest(request, response)) {//根据 loginurl判断的
            if (isLoginSubmission(request, response)) {//is post
                return executeLogin(request, response);
            } else {
                //allow them to see the login page ;)
                return true;//就不校验了
            }
        } else {
            saveRequestAndRedirectToLogin(request,response);
            //需要改成  UnauthorizedUrl  , saveRequestAndRedirectToLogin(); 会跳转到login
            return false;
        }

    }
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        WebUtils.issueRedirect(request, response, URLConstant.INDEX_URL);
    }

    protected void RedirectToSuccess(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
    }



}

