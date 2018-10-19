package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.UrlFilter;
import com.lianhe.nine.intface.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * shiro过滤链表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Transactional(rollbackFor = Exception.class)
public interface IUrlFilterService extends IBaseService<UrlFilter> {
    List<UrlFilter> getAllUrlFilter() throws Exception;
}
