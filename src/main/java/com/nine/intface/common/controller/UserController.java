package com.nine.intface.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
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

    @GetMapping(value = "/")
    public String index() throws Exception {
        return "my/layout/layout";
    }
    @GetMapping(value = "/user/{user_id}")
    public String get(@PathVariable int user_id, Model model) throws Exception {

        Result result;
        User user = userService.getById(user_id);
        if (user != null) {
            result = ResultFactory.getOKRestResult(user);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        model.addAttribute("Result",result);
        return "";
    }

  //  @RequiresRoles(RoleEnum.ADMIN)
    @PutMapping(value = "/user/{user_id}")
    public String update(@PathVariable int user_id, User user,Model model) throws Exception {
        Result result;
        user.setId(user_id);
        boolean bResult = userService.updateById(user);
        if (bResult) {
            result = ResultFactory.getOKRestResult(bResult);
        } else {
            result = ResultFactory.getFailedRestResult();
        }
        model.addAttribute("Result",result);
        return "";
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
        model.addAttribute("Result",result);

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
        model.addAttribute("Result",result);

        return "";
    }
   // @RequiresRoles(RoleEnum.ADMIN)
    @GetMapping(value = "/page/{page_index}")
    public String getAll(@PathVariable int page_index,Model model) throws Exception {
        Result result;
        PageInfo<User> pageInfo = userService.getPageByHelper(page_index, Constant.PAGE_SIZE.getIndex(), new QueryWrapper<>());
        result = ResultFactory.getOKRestResult(pageInfo);
        model.addAttribute("Result",result);
        return "my/userlist";
    }


}


