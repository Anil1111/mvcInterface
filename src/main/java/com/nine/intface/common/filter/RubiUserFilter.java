package com.nine.intface.common.filter;


import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.controller.Suger;
import org.apache.shiro.web.filter.authc.UserFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Rubi
 * @version : 2018-10-07 23:53 下午
 */
public class RubiUserFilter extends UserFilter implements Suger {

    /**
     * isAccessAllowed：表示是否允许访问；
     * 如果允许访问返回true，否则false；
     *
     * onAccessDenied：表示当访问拒绝时是否已经处理了；
     * 如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (isLoginRequest(request, response)) {
//            return true;
//        } else {
//            Subject subject = getSubject(request, response);
//            return subject.getPrincipal() != null;
//        }
//    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            resp.setStatus(HttpStatus.OK.value());
            return true;
        }
        sendAnExceptionResult(resp,Constant.EXCEPTION_AUTHENTICATION.getIndex(),Constant.EXCEPTION_AUTHENTICATION.getName());
        return false;
    }
}
