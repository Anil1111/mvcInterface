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

/**
 * <p>
 * 访问日志表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService,BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Override
    public void recordOne(HttpServletRequest request)throws Exception {
        String OperateBy=  Strings.isNotBlank(request.getHeader("User-Agent"))?
                request.getHeader("User-Agent"):Strings.EMPTY;
        String url = Strings.isNotBlank(request.getRequestURI())?request.getRequestURI():Strings.EMPTY;
        SysLog sysLog = new SysLog();
        sysLog.setIp(getIpAddress(request));
        sysLog.setOperate_by(OperateBy);//
        sysLog.setOperate_url(URLDecoder.decode(url,StandardCharsets.UTF_8.name()));//
        sysLog.setRemark(Strings.EMPTY);
        sysLog.setCreate_time(new Date());
        baseMapper.insert(sysLog);
    }

    @Override
    public void logOne(HttpServletRequest request) throws Exception {
        logger.info("uri    : " + URLDecoder.decode(request.getRequestURI(),StandardCharsets.UTF_8.name()));
        logger.info("params : "+this.getRequestMapSingle(request));
    }
}
