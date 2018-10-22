package com.lianhe.nine.intface.config;

import com.lianhe.nine.intface.service.ISysLogService;
import com.lianhe.nine.intface.service.IUrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Rubi
 * @version : 2018-10-14 18:21 下午
 */
public class    PreServiceConfig {
    @Autowired
    private IUrlFilterService shiroService;
//    @Autowired
//    private ISysLogService sysLogService;
}
