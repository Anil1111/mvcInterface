package com.lianhe.nine.intface.interceptpr;

import com.lianhe.nine.intface.controller.BaseHandler;
import com.lianhe.nine.intface.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author : Rubi
 * @version : 2018-10-11 10:36
 */
@Component("logAsync")
public class LogAsync implements BaseHandler, IAsync {
    private static final Logger logger = LoggerFactory.getLogger(LogAsync.class);
    
    @Autowired
    private ISysLogService sysLogService;
    @Async
    @Override
    public void recordOne(HttpServletRequest request)throws Exception{
        String ip = getIpAddress(request);
        String remark="";
        String operateUrl=URLDecoder.decode(request.getRequestURI(),StandardCharsets.UTF_8.name());
        String operateBy=request.getHeader("User-Agent");
        sysLogService.recordOne(ip,remark,operateUrl,operateBy);
    }
    @Async
    @Override
    public void logOne(HttpServletRequest request)throws Exception{
        logger.info("uri    : " + URLDecoder.decode(request.getRequestURI(),StandardCharsets.UTF_8.name()));
        logger.info("params : "+this.getRequestMapSingle(request));
    }

}
//package com.noob.po;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.extension.activerecord.Model;
//
//import java.util.Comparator;
//import java.util.Date;
//
//import com.baomidou.mybatisplus.annotation.TableId;
//
//import java.io.Serializable;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//
///**
// * <p>
// *
// * </p>
// *
// * @author Rubi
// * @since 2018-10-13
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
//public class ShiroUrlFilter extends Model<ShiroUrlFilter> implements Comparable<ShiroUrlFilter> {
//
//    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    private String uri;
//
//    private String filter;
//
//    private Integer parentSort;
//
//    private Integer subSort;
//
//    private String remark;
//
//    private Date createTime;
//
//    private Date updateTime;
//
//    private String creatorName;
//
//    private String updatorName;
//
//    private Integer isEnable;
//
//
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
//
//    @Override
//    public int compareTo(ShiroUrlFilter o) {
//        int superResult = parentSort.compareTo(o.getParentSort());
//        int subResult = subSort.compareTo(o.getSubSort());
//        return superResult != 0 ? superResult : subResult;
//    }
//}
