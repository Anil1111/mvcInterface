package com.nine.intface.testing.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.nine.intface.testing.service.IUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
                           @RequestParam(required = false, defaultValue = "{}") String params,
                           @RequestParam(required = false, defaultValue = "{}") String headers,
                           @RequestParam(required = false, defaultValue = "{}") String bodys,
                           @RequestParam(required = false) MultipartFile[] uploadFiles, @RequestParam(required = false) String[] fileNames) throws Exception {
        ResponseEntity<String> response =
                urlService.doHanle(scheme, method, host, port, path, file, params, headers, bodys, uploadFiles, fileNames, String.class);
        Object body;
        try {
            body = response.hasBody() ? JSON.parseObject(response.getBody()) : new JSONObject();
        } catch (JSONException e) {
            body = response.hasBody() ? response.getBody() : Strings.EMPTY;
        }
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
        map.put("body", body);
        map.put("header", response.getHeaders());
        map.put("statusCode", response.getStatusCodeValue());
        return map;
    }


}

