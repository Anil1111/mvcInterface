package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.SysLog;
import org.springframework.transaction.annotation.Transactional;

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

    int recordOne(String ip, String remark, String operateUrl, String operateBy) throws Exception;
}
