package com.nine.intface.project.service;

import com.nine.intface.common.service.IBaseService;
import com.nine.intface.project.po.Project;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */

@Transactional(rollbackFor = Exception.class)
public interface IProjectService extends IBaseService<Project> {

}
