package com.nine.intface.project.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.project.dao.ProjectUserMapper;
import com.nine.intface.project.po.ProjectUser;
import com.nine.intface.project.service.IProjectUserService;
import org.springframework.stereotype.Service;

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

}
