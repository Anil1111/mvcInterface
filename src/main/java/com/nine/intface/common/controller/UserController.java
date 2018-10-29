package com.nine.intface.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.po.User;
import com.nine.intface.common.service.IUserService;
import com.nine.intface.common.tools.CodeGenerator;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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


    @GetMapping(value = "/user/{user_id}")
    @ResponseBody
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

  //  @RequiresRoles(RoleEnum.ADMIN)
    @PutMapping(value = "/user/{user_id}")
    @ResponseBody
    public Result update(@PathVariable int user_id,User user) throws Exception {
        Result result;
        user.setId(user_id);
        user.setUpdateTime(new Date());
        user.setPassword(CodeGenerator.nineMD5(user.getPassword()));
        boolean bResult = userService.updateById(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        return  result;
    }
  //  @RequiresRoles(RoleEnum.ADMIN)
    @DeleteMapping(value = "/user/{user_id}")
    @ResponseBody
    public Result delete(@PathVariable int user_id) throws Exception {
        Result result;
            boolean bResult = userService.removeById(user_id);
            if (bResult) {
                result = ResultFactory.getOKRestResult(bResult);
            } else {
                result = ResultFactory.getFailedRestResult(bResult);
            }


        return  result;
    }
   // @RequiresRoles(RoleEnum.ADMIN)
    @PostMapping(value = "/user")
    @ResponseBody
    public Result insert(User user) throws Exception {
        Result result;
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPassword(CodeGenerator.nineMD5(user.getPassword()));
        boolean bResult = userService.save(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        return result;
    }
    @GetMapping(value = "/page/{page_index}")
    public String getAll(@PathVariable int page_index,Model model) throws Exception {
        Result result;
        Page<User> page = new Page<>(page_index,Constant.PAGE_SIZE.getIndex());
        IPage<User> users = userService.page(page, new QueryWrapper<>());
        result = ResultFactory.getOKRestResult(users);
        model.addAttribute("result",result);
        return "my/userlist";
    }
    @GetMapping(value = "/index")
    public String index() throws Exception {
        return "forward:/users/page/1";
    }

}


