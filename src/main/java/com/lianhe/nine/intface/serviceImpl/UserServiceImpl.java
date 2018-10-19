package com.lianhe.nine.intface.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lianhe.nine.intface.dao.*;
import com.lianhe.nine.intface.po.*;
import com.lianhe.nine.intface.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;





    @Override
    public Set<Role> getRoles(String username) throws Exception {
        //首先根据username查出用户 id
        User user = this.getByUsername(username);
        if (user != null) {
            //然后去User_Role表查询有这个user_id的role_id 应该是个list


            List<UserRole> user_role_list = userRoleMapper.selectList(
                    new QueryWrapper<UserRole>().
                            eq("id",user.getId()));
            List<Integer> role_id_list = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(user_role_list)) {
                for (UserRole role : user_role_list) {
                    role_id_list.add(role.getId());
                }


                List<Role> role_list =
                        roleMapper.selectList(
                                new QueryWrapper<Role>()
                                        .in("id",role_id_list));
                if (!CollectionUtils.isEmpty(role_list)) {
                    Set<Role> role_set = new HashSet<>(role_list);
                    return role_set;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public Set<Permission> getPermissions(String username) throws Exception {
        Set<Role> role_set = this.getRoles(username);//得到所有角色
        if (role_set != null && !role_set.isEmpty()) {
            List<Integer> role_id_list = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(role_set)) {
                for (Role role : role_set) {
                    role_id_list.add(role.getId());//得到所有角色id
                }

                List<RolePermission> role_per_list =
                        rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().in("role_id",role_id_list));//根据所有角色查所有权限
                if (!CollectionUtils.isEmpty(role_per_list)) {
                    List<Integer> per_id_list = Lists.newArrayList();
                    for (RolePermission drp : role_per_list) {
                        per_id_list.add(drp.getPermission_id());//得到所有权限id
                    }
                    List<Permission> per_list = permissionMapper.selectList(
                            new QueryWrapper<Permission>()
                                    .in("id",per_id_list));//根据权限id查所有详情
                    if (!CollectionUtils.isEmpty(per_list)) {
                        Set<Permission> per_set = Sets.newHashSet(per_list);
                        return per_set;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public User getByUsername(String username) throws Exception {
        User user = new User();


        List<User> list = baseMapper.selectList(new QueryWrapper<User>().eq("username",username));
        if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
            BeanUtils.copyProperties(list.get(0), user);
            return user;
        }
        return null;
    }



}
