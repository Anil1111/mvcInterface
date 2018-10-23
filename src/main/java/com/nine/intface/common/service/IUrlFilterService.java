package com.nine.intface.common.service;

import com.nine.intface.common.po.UrlFilter;
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
