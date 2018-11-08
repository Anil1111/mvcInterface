package com.nine.intface.testing.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlParamMapper;
import com.nine.intface.testing.po.UrlParam;
import com.nine.intface.testing.service.IUrlParamService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * url,param关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlParamServiceImpl extends BaseServiceImpl<UrlParamMapper, UrlParam> implements IUrlParamService {
    @Override
    public List<UrlParam> getParamsByUrlId(int url_id) throws Exception {
        List<UrlParam> list=baseMapper.selectList(new QueryWrapper<UrlParam>().eq("url_id",url_id));
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }
        return Lists.newArrayList();
    }
}
