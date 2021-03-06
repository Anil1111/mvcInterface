package com.nine.intface.testing.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.testing.po.Url;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Transactional(rollbackFor = Exception.class)
public interface IUrlService extends IBaseService<Url> {

    <T> ResponseEntity<T> doHanle(String scheme, String method, String host,
                                  Integer port, String path, String file, String params,
                                  String headers, String bodys, MultipartFile[] uploadFiles, String[] fileNames,
                                  Class<T> jsonObjectClass)throws Exception;

}
