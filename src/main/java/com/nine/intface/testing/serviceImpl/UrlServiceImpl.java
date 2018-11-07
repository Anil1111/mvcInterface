package com.nine.intface.testing.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.nine.intface.common.constants.MethodConstant;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlMapper;
import com.nine.intface.testing.po.Url;
import com.nine.intface.testing.service.IUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
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

    @Value("${rubi.file.upload.folder}")
    private String UPLOAD_FOLDER;

    /*
     * 根据method的不同 参数摆的位置不一样,url也不能加xx={},故先判断method type
     * 若是post 将参数放入 Entity
     *
     * */
    @Override
    public <T> ResponseEntity<T> doHanle(String scheme, String method, String host, Integer port, String path, String file,
                                         String params, String headers,
                                         String bodys, MultipartFile[] uploadFiles, String[] fileNames, Class<T> clazz) throws Exception {
//        boolean
        Map<String, Object> mapBodys = JSON.parseObject(bodys, Map.class);
        MultiValueMap<String, Object> requestBodys = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : mapBodys.entrySet()) {
            requestBodys.add(entry.getKey(), entry.getValue());
        }
        List<File> temps= Lists.newArrayList();
        if (uploadFiles.length > 0 && uploadFiles.length > 0) {
            for (int i = 0; i < fileNames.length; i++) {
                log.info("{}-->{}", uploadFiles[i].getOriginalFilename(), uploadFiles[i].getSize());
                File tempFile = new File(UPLOAD_FOLDER + "\\temp\\" + uploadFiles[i].getOriginalFilename());
                if (!tempFile.getParentFile().exists()) { //判断文件父目录是否存在
                    tempFile.getParentFile().mkdirs();
                }
                uploadFiles[i].transferTo(tempFile); //保存文件
                FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
                // 提醒一下，应为中间进行转化的时候会生成一个临时文件，
                // temFile在这里就表示生成的临时文件，
                // 这里只是相当于一个载体，在最后进行删除就行了。
                requestBodys.add(fileNames[i], fileSystemResource);
                temps.add(tempFile);
            }
        }
        Map<String, Object> mapParams = JSON.parseObject(params, Map.class);
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
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
        path = StringUtils.isEmpty(path) ? Strings.EMPTY : "/" + path;
        file = StringUtils.isEmpty(file) ? Strings.EMPTY : file;
        port = port != null && port <= 0 ? 80 : port;

        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://")
                .append(host).append(":").append(port).append(path).append("/").append(file).append("?");
        for (String paramName : requestParams.keySet()) {
            url.append(paramName).append("={").append(paramName).append("}&");
        }
        url.deleteCharAt(url.length() - 1);
        log.info("complete url: {}", url);
        switch (method) {
            case MethodConstant.DELETE:
                break;
            case MethodConstant.GET:
                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestHeaders);
                response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz, mapParams);
                return response;
            case MethodConstant.POST:
                break;
            case MethodConstant.PUT:
                break;
            default:
                throw new HttpRequestMethodNotSupportedException(method);
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBodys, requestHeaders);
//        response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz);
        response = restTemplate.exchange(url.toString(), HttpMethod.resolve(method), requestEntity, clazz, mapParams);
        for (File temp : temps) {
            temp.delete();
        }

        return response;
    }

}
