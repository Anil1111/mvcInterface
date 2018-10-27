package com.nine.intface.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.intface.common.constants.Constant;
import com.nine.intface.common.po.User;
import com.nine.intface.common.service.IUserService;
import com.nine.intface.common.vo.Result;
import com.nine.intface.common.vo.ResultFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

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
    public String get(@PathVariable int user_id, Model model) throws Exception {

        Result result;
        User user = userService.getById(user_id);
        if (user != null) {
            result = ResultFactory.getOKRestResult(user);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        model.addAttribute("result",result);
        return "";
    }

  //  @RequiresRoles(RoleEnum.ADMIN)
    @PutMapping(value = "/user/{user_id}")
    @ResponseBody
    public Result update(@PathVariable int user_id,User user,Model model) throws Exception {
        Result result;
        user.setId(user_id);
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
    public String delete(@PathVariable int user_id,Model model) throws Exception {
        Result result;
            boolean bResult = userService.removeById(user_id);
            if (bResult) {
                result = ResultFactory.getOKRestResult(bResult);
            } else {
                result = ResultFactory.getFailedRestResult(bResult);
            }
        model.addAttribute("result",result);

        return "";
    }
   // @RequiresRoles(RoleEnum.ADMIN)
    @PostMapping(value = "/user")
    public String insert(User user,Model model) throws Exception {
        Result result;
        boolean bResult = userService.save(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        model.addAttribute("result",result);

        return "";
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


