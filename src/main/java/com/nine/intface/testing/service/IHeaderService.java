package com.nine.intface.testing.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.testing.po.Header;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * header 表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */

@Transactional(rollbackFor = Exception.class)
public interface IHeaderService extends IBaseService<Header> {

}
