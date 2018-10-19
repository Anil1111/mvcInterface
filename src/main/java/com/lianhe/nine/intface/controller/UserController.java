package com.lianhe.nine.intface.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lianhe.nine.intface.constant.Constant;
import com.lianhe.nine.intface.constant.Role;
import com.lianhe.nine.intface.po.User;
import com.lianhe.nine.intface.service.IUserService;
import com.lianhe.nine.intface.vo.Result;
import com.lianhe.nine.intface.vo.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */

@Api(value = "/users", tags = {"用户管理"})
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(httpMethod = "GET", value = "根据id查询用户", tags = {"用户管理"}, notes = "需要admin权限")
    @ApiImplicitParam(name = "user_id", value = "用户id", dataType = "int", paramType = "path", example = "1")
    @RequiresRoles(Role.ADMIN)
    @GetMapping(value = "/user/{user_id}")
    public Result get(@PathVariable int user_id) throws Exception {

        Result result;
        User user = userService.getById(user_id);
        if (user != null) {
            result = ResultFactory.getOKRestResult(user);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        return result;
    }

    @ApiOperation(httpMethod = "PUT", value = "根据id更新用户", tags = {"用户管理"}, notes = "需要admin权限")
    @ApiImplicitParam(name = "user_id", value = "用户id", dataType = "int", paramType = "path", example = "1")
    @RequiresRoles(Role.ADMIN)
    @PutMapping(value = "/user/{user_id}")
    public Result update(@PathVariable int user_id, User user) throws Exception {
        Result result;
        user.setId(user_id);
        boolean bResult = userService.updateById(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        return result;
    }
    @ApiOperation(httpMethod = "DELETE", value = "根据id删除用户", tags = {"用户管理"}, notes = "需要admin权限")
    @ApiImplicitParam(name = "user_id", value = "用户id", dataType = "int", paramType = "path", example = "1")
    @RequiresRoles(Role.ADMIN)
    @DeleteMapping(value = "/user/{user_id}")
    public Result delete(@PathVariable int user_id) throws Exception {
        Result result;
            boolean bResult = userService.removeById(user_id);
            if (bResult) {
                result = ResultFactory.getOKRestResult(bResult);
            } else {
                result = ResultFactory.getFailedRestResult(bResult);
            }
        return result;
    }
    @ApiOperation(httpMethod = "POST", value = "新建一个用户", tags = {"用户管理"}, notes = "需要admin权限")
    @RequiresRoles(Role.ADMIN)
    @PostMapping(value = "/user")
    public Result insert(User user) throws Exception {
        Result result;
        boolean bResult = userService.save(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        return result;
    }
    @ApiOperation(httpMethod = "GET", value = "分页查询所有用户", tags = {"用户管理"}, notes = "需要admin权限")
    @ApiImplicitParam(name = "page_index", value = "当前页数", dataType = "int", paramType = "path", example = "1")
    @RequiresRoles(Role.ADMIN)
    @GetMapping(value = "/page/{page_index}")
    public Result getAll(@PathVariable int page_index) throws Exception {
        PageInfo<User> pageInfo = userService.getPageByHelper(page_index, Constant.PAGE_SIZE.getIndex(), new QueryWrapper<>());
        return ResultFactory.getOKJeasySult(pageInfo);
    }


}


