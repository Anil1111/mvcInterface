package com.nine.intface.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nine.intface.common.dao.*;
import com.nine.intface.common.po.*;
import com.nine.intface.common.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
            List<UserRole> userRoles = userRoleMapper.selectList(
                    new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            if (!CollectionUtils.isEmpty(userRoles)) {
                List<Integer> roleIds = Lists.newArrayList();
                for (UserRole userRole : userRoles) {
                    roleIds.add(userRole.getRoleId());
                }
                List<Role> roles = roleMapper.selectBatchIds(roleIds);
                if (!CollectionUtils.isEmpty(roles)) {
                    Set<Role> roleSet = Sets.newHashSet(roles);
                    return roleSet;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public Set<Permission> getPermissions(String username) throws Exception {
        Set<Role> roleSet = this.getRoles(username);//得到所有角色
        if (!CollectionUtils.isEmpty(roleSet)) {
            List<Integer> roleIds = Lists.newArrayList();
            for (Role role : roleSet) {
                roleIds.add(role.getId());//得到所有角色id
            }
            List<RolePermission> rolePermissions =
                    rolePermissionMapper.selectBatchIds(roleIds);//根据所有角色查所有权限
            if (!CollectionUtils.isEmpty(rolePermissions)) {
                List<Integer> permissionIds = Lists.newArrayList();
                for (RolePermission drp : rolePermissions) {
                    permissionIds.add(drp.getPermissionId());//得到所有权限id
                }
                List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);//根据权限id查所有详情
                if (!CollectionUtils.isEmpty(permissions)) {
                    Set<Permission> permissionSet = Sets.newHashSet(permissions);
                    return permissionSet;
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
        List<User> list = baseMapper.selectList(new QueryWrapper<User>().eq("username", username));
        if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }


}
