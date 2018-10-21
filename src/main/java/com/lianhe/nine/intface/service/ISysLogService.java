package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.SysLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 访问日志表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Transactional(rollbackFor = Exception.class)
public interface ISysLogService extends IBaseService<SysLog> {

    void recordOne(HttpServletRequest request) throws Exception;



    void logOne(HttpServletRequest request)throws Exception;
}
