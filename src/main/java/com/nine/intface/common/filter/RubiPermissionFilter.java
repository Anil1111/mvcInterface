package com.nine.intface.common.filter;




import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.controller.BaseHandler;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Rubi
 * @version : 2018-10-07 20:35 下午
 */
public class RubiPermissionFilter extends PermissionsAuthorizationFilter implements BaseHandler {


    private static final Logger logger = LoggerFactory.getLogger(RubiPermissionFilter.class);
    

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
