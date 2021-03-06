package com.nine.intface.common.service;

import com.nine.intface.common.po.SysLog;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

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
    /**
     * 记录日志到数据库
     * @param ip
     * @param operateBy
     * @param operateUrl
     * @param remark
     * @param createTime
     * @throws Exception
     */
    void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime) ;
    /**
     * 打印日志到控制台和文件
     * @param method
     * @param url
     * @param params
     * @throws Exception
     */
    void logOne(String method,String url, Map params,String contentType);
}
