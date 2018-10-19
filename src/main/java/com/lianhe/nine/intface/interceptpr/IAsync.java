package com.lianhe.nine.intface.interceptpr;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Rubi
 * @version : 2018-10-12 9:04
 */
public interface IAsync {
    void recordOne(HttpServletRequest request) throws Exception;



    void logOne(HttpServletRequest request) throws Exception;
}
