package com.nine.intface.testing.service;

import com.nine.intface.common.service.IBaseService;
import com.nine.intface.testing.po.UrlParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * url,param关系表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Transactional(rollbackFor = Exception.class)
public interface IUrlParamService extends IBaseService<UrlParam> {

}
