package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.Project;
import com.lianhe.nine.intface.dao.ProjectMapper;
import com.lianhe.nine.intface.service.IProjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectMapper, Project> implements IProjectService {

}
