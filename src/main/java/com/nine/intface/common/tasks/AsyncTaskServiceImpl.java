package com.nine.intface.common.tasks;

import com.nine.intface.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-22 10:06
 */
@Slf4j
@Service
public class AsyncTaskServiceImpl implements IAsyncTaskService {

//    @Autowired
//    private ISysLogService sysLogService;
//
//    @Override
//    public void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime) throws Exception {
//        sysLogService.recordOne(ip, operateBy, operateUrl, remark, createTime);
//    }
//
//    @Override
//    public void logOne(String method,String url, Map params) throws Exception {
//        sysLogService.logOne(method,url, params);
//    }


}
