package com.nine.intface.common.tools;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Rubi
 * @version : 2018-10-17 16:29
 */
public class RestTool {
    public static String getWithStream(String url, MultiValueMap<String, Object> params, RestTemplate restTemplate){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/octet-stream; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String jsonStr = JSONObject.toJSONString(params);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonStr, headers);
        String result = restTemplate.getForObject(url,String.class,formEntity);
        return result;
    }

}
