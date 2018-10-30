package com.nine.intface.testing.controller;


import com.nine.intface.testing.service.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUrlService urlService;

    @RequestMapping("/do_handle")
    public Object doHandle(@RequestParam(required = false) String scheme, @RequestParam(required = false) String method,
                           @RequestParam(required = false) String host, @RequestParam(required = false) Integer port,
                           @RequestParam(required = false) String path, @RequestParam(required = false) String file,
                           @RequestBody @RequestParam(required = false) MultiValueMap<String, String> rparams, @RequestParam(required = false) MultiValueMap<String, String> headers,
                           @RequestParam(required = false) MultiValueMap<String, String> bodys) throws Exception {
        String result = urlService.doHanle(scheme, method, host, port, path, file, rparams, headers, bodys, String.class);
        System.out.println(result);
        return result;
    }
}

