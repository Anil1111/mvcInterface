package com.nine.intface.project.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.project.dao.ProjectMapper;
import com.nine.intface.project.dao.ProjectUserMapper;
import com.nine.intface.project.po.Project;
import com.nine.intface.project.po.ProjectUser;
import com.nine.intface.project.service.IProjectUserService;
import com.nine.intface.testing.dao.EnvironmentMapper;
import com.nine.intface.testing.po.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目,user关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class ProjectUserServiceImpl extends BaseServiceImpl<ProjectUserMapper, ProjectUser> implements IProjectUserService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Override
    public List<Project> listByUserId(int user_id) throws Exception {
        List<ProjectUser> list = baseMapper.selectList(new QueryWrapper<ProjectUser>().select("project_id").eq("user_id", user_id));
        if (!CollectionUtils.isEmpty(list) ) {
            List<Integer> ids = Lists.newArrayList();
            for (ProjectUser projectUser : list) {
                ids.add(projectUser.getProjectId());
            }
            List<Project> result=projectMapper.selectBatchIds(ids);
            return result;
        }else {
            return Lists.newArrayList();
        }

    }

    @Override
    public List<Environment> listEnvById(int project_id) throws Exception {
        List<Environment> list = environmentMapper.selectList(new QueryWrapper<Environment>().eq("project_id", project_id));
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }else {
            return Lists.newArrayList();
        }
    }
}
