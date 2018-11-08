package com.nine.intface.common.filter;

import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.controller.Sugar;
import com.nine.intface.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author : Rubi
 * @version : 2018-10-07 20:04 下午
 */
@Slf4j
public class RubiRoleFilter extends RolesAuthorizationFilter implements Sugar {

    private ISysLogService sysLogService;



//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
////        if(req.getRequestURI().contains(URLConstant.ACTUATOR)){
////
////            if(sysLogService == null){
////                sysLogService =SpringUtils.getBean(ISysLogService.class);
////            }
////            /**
////             * 因为 actuator  以及子地址都不会被拦截器拦截， 所以专门用过滤器打印 改日志
////             */
////            try {
////                sysLogService.recordOne(
////                        getIpAddress(req),req.getHeader("User-Agent"),
////                        URLDecoder.decode(req.getRequestURI(), StandardCharsets.UTF_8.name()),Strings.EMPTY,new Date());
////                sysLogService.logOne(
////                        req.getMethod(),
////                        URLDecoder.decode(req.getRequestURI(), StandardCharsets.UTF_8.name())
////                        ,this.getRequestMapSingle(req));
////            } catch (Exception e) {
////                log.warn("filter failed record mysql :{}",e.getClass().getName());
////            }
////        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            resp.setStatus(HttpStatus.OK.value());
            return true;
        }
        sendAnExceptionResult(resp,Constant.EXCEPTION_UNAUTHORIZED.getIndex(),Constant.EXCEPTION_UNAUTHORIZED.getName());
        return false;
    }


}
// 前端Ajax请求时requestHeader里面带一些参数，用于判断是否是前端的请求
//        String ajaxHeader = req.getHeader(ShiroConstant.USERID);
//        if (ajaxHeader != null || req.getHeader("wkcheck") != null) {
//            //前端Ajax请求，则不会重定向
//            resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
//            resp.setHeader("Access-Control-Allow-Credentials", "true");
//            resp.setContentType("application/json; charset=utf-8");
//            resp.setCharacterEncoding(StandardCharsets.UTF_8);
//            PrintWriter out = resp.getWriter();
//            JSONObject result = new JSONObject();
//            result.put("message", "权限不足！");
//            result.put("statusCode", -403);
//            out.println(result);
//            out.flush();
//            out.close();
//            return false;
//        }
//        return super.onAccessDenied(request, response);