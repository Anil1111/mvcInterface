package com.lianhe.nine.intface.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lianhe.nine.intface.po.UrlFilter;
import com.lianhe.nine.intface.dao.UrlFilterMapper;
import com.lianhe.nine.intface.service.IUrlFilterService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * shiro过滤链表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class UrlFilterServiceImpl extends BaseServiceImpl<UrlFilterMapper, UrlFilter> implements IUrlFilterService {
	 @Override
    public List<UrlFilter> getAllUrlFilter() throws Exception {
        List<UrlFilter> list=list(new QueryWrapper<UrlFilter>().eq("disable_flag",1));
        Collections.sort(list);
        return list;
    }

}	
