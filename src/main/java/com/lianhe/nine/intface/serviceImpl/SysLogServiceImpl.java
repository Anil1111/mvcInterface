package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.SysLog;
import com.lianhe.nine.intface.dao.SysLogMapper;
import com.lianhe.nine.intface.service.ISysLogService;
import com.lianhe.nine.intface.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public int recordOne(String ip, String remark, String operateUrl, String operateBy)throws Exception {
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setOperate_by(operateBy);
        sysLog.setOperate_url(operateUrl);
        sysLog.setRemark(remark);
        sysLog.setCreate_time(new Date());
        return baseMapper.insert(sysLog);
    }
}
