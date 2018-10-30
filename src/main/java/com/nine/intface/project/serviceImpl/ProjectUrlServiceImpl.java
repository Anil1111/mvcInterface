package com.nine.intface.project.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.project.dao.ProjectUrlMapper;
import com.nine.intface.project.po.ProjectUrl;
import com.nine.intface.project.service.IProjectUrlService;
import com.nine.intface.testing.dao.UrlMapper;
import com.nine.intface.testing.po.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 项目,url关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class ProjectUrlServiceImpl extends BaseServiceImpl<ProjectUrlMapper, ProjectUrl> implements IProjectUrlService {

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public List<Url> listUrlsById(int project_id) throws Exception {
        List<ProjectUrl> list = baseMapper.selectList(new QueryWrapper<ProjectUrl>().select("url_id").eq("project_id", project_id));
        if (!CollectionUtils.isEmpty(list)) {
            List<Integer> ids = Lists.newArrayList();
            for (ProjectUrl projectUrl : list) {
                ids.add(projectUrl.getUrlId());
            }
            List<Url> result = urlMapper.selectBatchIds(ids);
            return result;
        } else {
            return Lists.newArrayList();
        }
    }
}

