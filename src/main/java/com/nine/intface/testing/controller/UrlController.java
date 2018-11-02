package com.nine.intface.testing.controller;


import com.alibaba.fastjson.JSONObject;
import com.nine.intface.testing.service.IUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Slf4j
@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private IUrlService urlService;

    @RequestMapping("/do_handle")
    public Object doHandle(@RequestParam(required = false) String scheme, @RequestParam(required = false) String method,
                           @RequestParam(required = false) String host, @RequestParam(required = false) Integer port,
                           @RequestParam(required = false) String path, @RequestParam(required = false) String file,
                           @RequestParam(required = false,defaultValue = "{}") String params, @RequestParam(required = false,defaultValue = "{}") String headers,
                           @RequestParam(required = false,defaultValue = "{}") String bodys) throws Exception {
        JSONObject result = urlService.doHanle(scheme, method, host, port, path, file, params, headers, bodys, JSONObject.class);
        log.info("doHandle Result: {}",result);
        return result;
    }
}

