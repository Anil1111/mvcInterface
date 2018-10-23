package com.nine.intface.common.interceptpr;


import com.nine.intface.common.controller.BaseHandler;
import com.nine.intface.common.service.ISysLogService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 打印日志到数据库
 * @author : Rubi
 * @version : 2018-10-10 15:45
 */
public class SystemLogInterceptor implements HandlerInterceptor,BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogInterceptor.class);

    @Autowired
    private ISysLogService sysLogService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        sysLogService.recordOne(
                getIpAddress(request),request.getHeader("User-Agent"),
                URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8.name()),Strings.EMPTY,new Date());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
