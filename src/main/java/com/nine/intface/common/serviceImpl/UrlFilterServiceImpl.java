package com.nine.intface.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Ordering;
import com.nine.intface.common.po.UrlFilter;
import com.nine.intface.common.dao.UrlFilterMapper;
import com.nine.intface.common.po.User;
import com.nine.intface.common.service.IUrlFilterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
//        Collections.sort(list, (UrlFilter o1,UrlFilter o2)->{
//            int superResult = o1.getParentSort().compareTo(o2.getParentSort());
//            int subResult = o1.getSubSort().compareTo(o2.getSubSort());
//            return superResult != 0 ? superResult : subResult;
//        });
         Ordering<UrlFilter> ordering =Ordering
                 .from(Comparator.comparing(UrlFilter::getParentSort))
                 .compound(Comparator.comparing(UrlFilter::getSubSort));
         Collections.sort(list,ordering);
        return list;
    }

}

/**
 *  int superResult = parentSort.compareTo(o.getParentSort());
 *         int subResult = subSort.compareTo(o.getSubSort());
 *         return superResult != 0 ? superResult : subResult;
 */
