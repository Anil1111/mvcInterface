package com.lianhe.nine.intface.interceptpr;


import com.lianhe.nine.intface.controller.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Rubi
 * @version : 2018-10-10 15:45
 */
public class SystemLogInterceptor implements HandlerInterceptor,BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogInterceptor.class);

    @Autowired
    private IAsync logAsync;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logAsync.recordOne(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
