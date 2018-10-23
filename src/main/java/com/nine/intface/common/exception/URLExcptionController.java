package com.nine.intface.common.exception;


import com.nine.intface.common.controller.BaseHandler;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-10 13:23
 */
@Controller
public class URLExcptionController extends BasicErrorController implements BaseHandler {


    public URLExcptionController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(body, status);
        return null;
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        //请求的状态

        sendAnExceptionResult(response,-997,"页面不存在!");

//        HttpStatus status = getStatus(request);
//        response.setStatus(getStatus(request).value());
//
//        Map<String, Object> model = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
//        //指定自定义的视图
//        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);

        return null;
    }


}
