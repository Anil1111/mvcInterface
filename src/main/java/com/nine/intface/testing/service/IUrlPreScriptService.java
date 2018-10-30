package com.nine.intface.testing.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.testing.po.UrlPreScript;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * url,pre_script关系表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Transactional(rollbackFor = Exception.class)
public interface IUrlPreScriptService extends IBaseService<UrlPreScript> {

}
