package com.nine.intface.project.service;


import com.nine.intface.common.service.IBaseService;
import com.nine.intface.project.po.ProjectUrl;
import com.nine.intface.testing.po.Url;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 项目,url关系表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */

@Transactional(rollbackFor = Exception.class)
public interface IProjectUrlService extends IBaseService<ProjectUrl> {

    List<Url> listUrlsById(int project_id)throws Exception;
}
