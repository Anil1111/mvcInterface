package com.nine.intface.common.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-10 10:27
 */
public interface Suger {
    default boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 获取表单格式数据(或url拼接参数)
     *
     * @return map
     */

    default Map<String,String> getHeaderMap(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,String> headerMap = Maps.newHashMap();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        return headerMap;
    }

    /**
     * 得到ip地址
     *
     * @return String
     */
    default String getIpAddress(HttpServletRequest request) {
        String ip = request.getRemoteAddr() == null ? Strings.EMPTY : request.getRemoteAddr();
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取服务器ip地址
     *
     * @return String
     */
    default String getServerIpAddress() {
        InetAddress address;
        String serverIpAddress = null;
        try {
            address = InetAddress.getLocalHost(); //获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            serverIpAddress = address.getHostAddress();//192.168.0.121
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return serverIpAddress;
    }

    /**
     * 允许跨域访问
     */
    default void allowCrossDomainAccess(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Access-Control-Allow-Headers:x-requested-with", "content-type");
    }

//    default String getParameter(String paraName,HttpServletRequest request) {
//        return request.getParameter(paraName);
//    }
//
//    default Map getParameterMap(HttpServletRequest request) {
//        return request.getParameterMap();
//    }

    /**
     * 获取请求属性封装为Map类型
     *
     * @return
     */
    default Map<String, String> getRequestMapSingle(HttpServletRequest request) {
        Map<String, String> conditions = Maps.newHashMap();
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            conditions.put(key, map.get(key)[0]);
        }
        return conditions;
    }
    /**
     * ajax跳转   url.js认识的 方式
     * @param url
     * @param request
     * @param response
     */
    default void sendRedirectUri(String url, HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        response.setHeader("REDIRECT", "REDIRECT");
        //告诉ajax我重定向的路径
        response.setHeader("CONTENTPATH", contextPath + url);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     *
     * @param response
     * @param code
     * @param message
     */
    default void sendAnExceptionResult(HttpServletResponse response, int code, String message) {
//        response.setHeader("Access-Control-Allow-Origin", response.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // Writers
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Result result = ResultFactory.getExceptionResult(code, message);
            String json = JSON.toJSONString(result);
            out.println(json);
            out.flush();

        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            out.close();
        }


    }
    /**
     *  使用	response 输出JSON
     * @param response
     * @param result
     * @throws IOException
     */
    default  void sendResult(ServletResponse response, Result result) throws IOException {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());//设置编码
            response.setContentType("application/json");//设置返回类型
            out = response.getWriter();
            out.println(JSONObject.toJSON(result));//输出
        } catch (Exception e) {
            throw e;
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

    default Map<String,Object> getSimple(String url ,Map<String,Object> paramMap,RestTemplate template){
        StringBuffer sbUrl = new StringBuffer(url);
        sbUrl.append("?");
        for (String paramName : paramMap.keySet()) {
            sbUrl.append(paramName).append("=").append(paramMap.get(paramName)).append("&");
        }
        sbUrl.deleteCharAt(sbUrl.length() - 1);
        String json = template.getForObject(sbUrl.toString(),String.class);
        Map<String,Object> object = JSON.parseObject(json,Map.class);
        return object;
    }
}
