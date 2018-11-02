package com.nine.intface.common.interceptpr;


import com.nine.intface.common.controller.BaseHandler;
import com.nine.intface.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * 打印日志到控制台,文件
 * @author : Rubi
 * @version : 2018-10-10 15:59
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor,BaseHandler {
    @Autowired
    private ISysLogService sysLogService;


    /**
     * handler执行前,身份认证，身份授权
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        String uri = URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8.name());
        Map params = this.getRequestMapSingle(request);
        String contentType = request.getContentType();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        StringBuffer body = new StringBuffer();
//        String line = " ";
//        while ((line = reader.readLine()) != null){
//            body.append(line);
//        }
//        reader.close();
        sysLogService.logOne(method,uri,params,contentType);
        return true;
    }

    /**
     * 进入了handler,返回ModelAndView前，将公用数据传到视图  只要有一个拦截器的pre不放行 ，那么所有拦截器都不执行post
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * handler完成后,异常处理，日志(日志拦截器在xml，配置要放前面,
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
