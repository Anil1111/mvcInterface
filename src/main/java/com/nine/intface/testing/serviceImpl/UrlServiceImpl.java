package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.constants.MethodConstant;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlMapper;
import com.nine.intface.testing.po.Url;
import com.nine.intface.testing.service.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
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
                         MultiValueMap<String, String> requestParams, MultiValueMap<String, String> requestHeaders,
                         MultiValueMap<String, String> requestBodys, Class<T> clazz) throws Exception {
        ResponseEntity<T> response;
        HttpHeaders httpHeaders = new HttpHeaders();
        //如果不传入method ,默认get
        if (StringUtils.isEmpty(method)) {
            method = HttpMethod.GET.name();
        }
        method=  method.toUpperCase();
        port = port != null && port != 0 ? port : 80;
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(host).append(":").append(port).append("/")
                .append(path).append("/").append(file);
        //  String uri = "http://localhost/interface/users/user";
        switch (method) {
            case MethodConstant.DELETE:
                break;
            case MethodConstant.GET:
                url.append("?");
                for (String paramName : requestParams.keySet()) {
                    url.append(paramName).append("=").append("{").append(paramName).append("}").append("&");
                }
                url.deleteCharAt(url.length() - 1);
                httpHeaders.addAll(requestHeaders);
                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestHeaders);
                response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz, requestParams);
                return response.getBody();
            case MethodConstant.POST:
                break;
            case MethodConstant.PUT:
                break;
            default:
                throw new Exception("Not support method yet.");
        }
        System.out.println(requestParams);
        httpHeaders.addAll(requestHeaders);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, requestHeaders);
        response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz);
        return response.getBody();
    }
}
