package com.nine.intface.testing.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.nine.intface.common.constants.MethodConstant;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlMapper;
import com.nine.intface.testing.po.Url;
import com.nine.intface.testing.service.IUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Slf4j
@Service
public class UrlServiceImpl extends BaseServiceImpl<UrlMapper, Url> implements IUrlService {

    @Autowired
    private RestTemplate restTemplate;

    /*
     * 根据method的不同 参数摆的位置不一样,url也不能加xx={},故先判断method type
     * 若是post 将参数放入 Entity
     * */
    @Override
    public <T> T doHanle(String scheme, String method, String host, Integer port, String path, String file,
                         String params, String headers,
                         String bodys, Class<T> clazz) throws Exception {
//        boolean
        Map<String, String> mapBodys = JSON.parseObject(bodys, Map.class);
        MultiValueMap<String, String> requestBodys = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : mapBodys.entrySet()) {
            requestBodys.add(entry.getKey(), entry.getValue());
        }
        Map<String, String> mapParams = JSON.parseObject(params, Map.class);
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : mapParams.entrySet()) {
            requestParams.add(entry.getKey(), entry.getValue());
        }
        Map<String, String> mapHeaders = JSON.parseObject(headers, Map.class);
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : mapHeaders.entrySet()) {
            requestHeaders.add(entry.getKey(), entry.getValue());
        }
        ResponseEntity<T> response;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.addAll(requestHeaders);
        scheme = StringUtils.isEmpty(scheme) ? "http" : scheme;
        host = StringUtils.isEmpty(headers) ? "127.0.0.1" : host;
        method = StringUtils.isEmpty(method) ? HttpMethod.GET.name() : method.toUpperCase();
        path = StringUtils.isEmpty(path) ? Strings.EMPTY : "/"+path;
        file = StringUtils.isEmpty(file) ? Strings.EMPTY : file;
        port = port != null && port <= 0 ? 80 : port;

        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://")
                .append(host).append(":").append(port).append(path).append("/").append(file).append("?");
        for (String paramName : requestParams.keySet()) {
            url.append(paramName).append("={").append(paramName).append("}&");
        }
        url.deleteCharAt(url.length() - 1);
        log.info("complete url: {}",url);
        switch (method) {
            case MethodConstant.DELETE:
                break;
            case MethodConstant.GET:
                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestHeaders);
                response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz, mapParams);
                return response.getBody();
            case MethodConstant.POST:
                break;
            case MethodConstant.PUT:
                break;
            default:
                throw new HttpRequestMethodNotSupportedException(method);
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBodys, requestHeaders);
//        response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz);
        response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz, mapParams);

        return response.getBody();
    }
}
