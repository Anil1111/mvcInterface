package com.nine.intface.common.serviceImpl;

import com.nine.intface.common.controller.Suger;
import com.nine.intface.common.dao.SysLogMapper;
import com.nine.intface.common.po.SysLog;
import com.nine.intface.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService, Suger {

    @Override
    public void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime)  {
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setOperateBy(operateBy);
        sysLog.setOperateUrl(operateUrl);
        sysLog.setRemark(remark);
        sysLog.setCreateTime(createTime);
        baseMapper.insert(sysLog);
    }

    @Override
    public void logOne(String method,String url, Map params,String contentType) {
        log.info("method      : {}",method);
        log.info("contentType : {}",contentType);
        log.info("uri         : {}",url);
        log.info("params      : {}",params);
    }
}
