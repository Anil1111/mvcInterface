package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.RolePermission;
import com.lianhe.nine.intface.dao.RolePermissionMapper;
import com.lianhe.nine.intface.service.IRolePermissionService;
import com.lianhe.nine.intface.serviceImpl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
