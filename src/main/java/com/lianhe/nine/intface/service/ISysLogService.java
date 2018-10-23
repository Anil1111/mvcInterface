package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.SysLog;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime) throws Exception;
    /**
     * 打印日志到控制台和文件
     * @param url
     * @param params
     * @throws Exception
     */
    void logOne(String url, Map params)throws Exception;
}
