package com.nine.intface.project.serviceImpl;

import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.project.dao.ProjectMapper;
import com.nine.intface.project.po.Project;
import com.nine.intface.project.service.IProjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectMapper, Project> implements IProjectService {

}
