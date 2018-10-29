package com.nine.intface.common.serviceImpl;

import com.nine.intface.common.controller.BaseHandler;
import com.nine.intface.common.dao.SysLogMapper;
import com.nine.intface.common.po.SysLog;
import com.nine.intface.common.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 访问日志表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService, BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Override
    public void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime) throws Exception {
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setOperateBy(operateBy);
        sysLog.setOperateUrl(operateUrl);
        sysLog.setRemark(remark);
        sysLog.setCreateTime(createTime);
        baseMapper.insert(sysLog);
    }

    @Override
    public void logOne(String method,String url, Map params) throws Exception {
        logger.info("method : {}",method);
        logger.info("uri    : {}",url);
        logger.info("params : {}",params);
    }
}
