package com.nine.intface.project.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.project.po.Project;
import com.nine.intface.project.po.ProjectUser;
import com.nine.intface.testing.po.Environment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 项目,user关系表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */

@Transactional(rollbackFor = Exception.class)
public interface IProjectUserService extends IBaseService<ProjectUser> {

    List<Project> listByUserId(int user_id)throws  Exception;


    List<Environment> listEnvById(int project_id)throws  Exception;
}
