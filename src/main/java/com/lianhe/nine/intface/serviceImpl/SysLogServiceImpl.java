package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.controller.BaseHandler;
import com.lianhe.nine.intface.dao.SysLogMapper;
import com.lianhe.nine.intface.po.SysLog;
import com.lianhe.nine.intface.service.ISysLogService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            logger.info("{}:{}",1,Thread.currentThread().getName());

        }
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setOperate_by(operateBy);
        sysLog.setOperate_url(operateUrl);
        sysLog.setRemark(remark);
        sysLog.setCreate_time(createTime);
        baseMapper.insert(sysLog);
    }

    @Override
    public void logOne(String url, Map params) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            logger.info("{}:{}",2,Thread.currentThread().getName());

        }
        logger.info("uri    : " + url);
        logger.info("params : " + params);
    }
}
