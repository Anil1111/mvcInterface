package com.nine.intface.common.filter;

import com.nine.intface.common.controller.Sugar;
import com.nine.intface.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-11-01 17:03 下午
 */
@Slf4j
@WebFilter(filterName="logFilter",urlPatterns= {"/*"})
@Order(FilterRegistrationBean.HIGHEST_PRECEDENCE)
public class LogFilter implements Filter, Sugar {

    private String[] excludeUrls={".js",".css",".jpg",".jpeg",".png",".gif"};
    @Autowired
    private ISysLogService sysLogService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       // excludeUrls = filterConfig.getInitParameter(URLConstant.SUFFIXPATTONS).split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8.name());

        for (String excludeUrl : excludeUrls) {
            if (uri.endsWith(excludeUrl)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String method = request.getMethod();
        Map params = this.getRequestMapSingle(request);
        String contentType = request.getContentType() != null ? request.getContentType() : Strings.EMPTY;
        String ip = getIpAddress(request);
        String operateBy = request.getHeader("User-Agent");
        String remark = Strings.EMPTY;
        sysLogService.logOne(method, uri, params, contentType);
        sysLogService.recordOne(ip, operateBy, uri, remark, new Date());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
