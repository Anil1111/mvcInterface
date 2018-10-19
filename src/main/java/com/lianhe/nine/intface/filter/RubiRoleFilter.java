package com.lianhe.nine.intface.filter;

import com.lianhe.nine.intface.constant.Constant;
import com.lianhe.nine.intface.controller.BaseHandler;
import com.lianhe.nine.intface.interceptpr.IAsync;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RubiRoleFilter extends RolesAuthorizationFilter implements BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(RubiRoleFilter.class);

    @Autowired
    private IAsync logAsync;

    public RubiRoleFilter(IAsync logAsync) {
        this.logAsync=logAsync;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {



        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getRequestURI().contains("actuator")){
            /**
             * 因为 actuator  以及子地址都不会被拦截器拦截， 所以专门用过滤器打印 改日志
             */
            try {
                logAsync.recordOne(req);
                logAsync.logOne(req);
            } catch (Exception e) {
                logger.warn("filter failed record mysql :{}",e.getClass().getName());
            }
        }
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