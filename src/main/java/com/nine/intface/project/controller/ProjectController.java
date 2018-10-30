package com.nine.intface.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import com.nine.intface.project.po.Project;
import com.nine.intface.project.po.ProjectUrl;
import com.nine.intface.project.service.IProjectService;
import com.nine.intface.project.service.IProjectUrlService;
import com.nine.intface.project.service.IProjectUserService;
import com.nine.intface.testing.po.Environment;
import com.nine.intface.testing.po.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private IProjectUrlService projectUrlService;
    @Autowired
    private IProjectUserService projectUserService;


    @GetMapping("/project/{user_id}")
    public Result listByUserId(@PathVariable int user_id) throws Exception {
        Result result;
        List<Project> list = projectUserService.listByUserId(user_id);
        result = ResultFactory.getOKRestResult(list);
        return result;
    }

    @GetMapping("/url/{project_id}")
    public Result getUrlsById(@PathVariable int project_id) throws Exception {
        Result result;
        List<Url> list = projectUrlService.listUrlsById(project_id);
        result = ResultFactory.getOKRestResult(list);
        return result;
    }

    @GetMapping("/environment/{project_id}")
    public Result getEnvironmentsById(@PathVariable int project_id) throws Exception {
        Result result;
        List<Environment> list = projectUserService.listEnvById(project_id);
        result = ResultFactory.getOKRestResult(list);
        return result;
    }
}

