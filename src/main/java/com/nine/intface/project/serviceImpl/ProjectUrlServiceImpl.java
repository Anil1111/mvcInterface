package com.nine.intface.project.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.project.dao.ProjectUrlMapper;
import com.nine.intface.project.po.ProjectUrl;
import com.nine.intface.project.service.IProjectUrlService;
import org.springframework.stereotype.Service;

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

}
