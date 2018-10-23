package com.lianhe.nine.intface.tasks;

import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-22 10:58
 */

public interface IAsyncTaskService {
    @Async
    void logOne(String url, Map params)throws Exception;
    @Async
    void recordOne(String ip, String operateBy, String operateUrl, String remark, Date createTime)throws Exception;

}
