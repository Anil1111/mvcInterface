package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.UserRole;
import com.lianhe.nine.intface.dao.UserRoleMapper;
import com.lianhe.nine.intface.service.IUserRoleService;
import com.lianhe.nine.intface.serviceImpl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
