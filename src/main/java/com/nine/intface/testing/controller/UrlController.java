package com.nine.intface.testing.controller;


import com.nine.intface.testing.service.IUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@RestController
@RequestMapping("/url")
public class UrlController {
    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private IUrlService urlService;

    @RequestMapping("/do_handle")
    public Object doHandle(@RequestParam(required = false) String scheme, @RequestParam(required = false) String method,
                           @RequestParam(required = false) String host, @RequestParam(required = false) Integer port,
                           @RequestParam(required = false) String path, @RequestParam(required = false) String file,
                           @RequestParam(required = false,defaultValue = "{}") String params, @RequestParam(required = false,defaultValue = "{}") String headers,
                           @RequestParam(required = false,defaultValue = "{}") String bodys) throws Exception {
        String result = urlService.doHanle(scheme, method, host, port, path, file, params, headers, bodys, String.class);
        logger.info("doHandle Result: {}",result);
        return result;
    }
}

