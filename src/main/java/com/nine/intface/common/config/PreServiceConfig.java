package com.nine.intface.common.config;

import com.nine.intface.common.service.ISysLogService;
import com.nine.intface.common.service.IUrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Rubi
 * @version : 2018-10-14 18:21 下午
 */
public class PreServiceConfig {
    @Autowired
    private IUrlFilterService shiroService;


}
