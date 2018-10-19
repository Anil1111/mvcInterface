package com.lianhe.nine.intface.tools;


import com.lianhe.nine.intface.exception.NullParameterException;

/**
 * @description: ${description}
 * @author: wangyijie
 * @create: 2018-09-08 11:31
 */
public class ParamUtil {
    public static void validateEmpty(String value,String name) throws NullParameterException {
        if (value == null || value.length() == 0) {
            throw new NullParameterException(name+" is null!");
        }

    }
}
