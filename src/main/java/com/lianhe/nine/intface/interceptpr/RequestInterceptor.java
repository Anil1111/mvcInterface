package com.lianhe.nine.intface.interceptpr;


import com.lianhe.nine.intface.controller.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestInterceptor implements HandlerInterceptor,BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Autowired
    private IAsync logAsync;

    @Override//handler执行前,身份认证，身份授权
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //logger.info("ip " +request.getRemoteAddr());
        //String action = request.getParameter("action")!=null? request.getParameter("action"):"";
        logAsync.logOne(request);
        return true;
    }

    @Override//进入了handler,返回ModelAndView前，将公用数据传到视图  只要有一个拦截器的pre不放行 ，那么所有拦截器都不执行post
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override//handler完成后,异常处理，日志(日志拦截器在xml，配置要放前面,
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
