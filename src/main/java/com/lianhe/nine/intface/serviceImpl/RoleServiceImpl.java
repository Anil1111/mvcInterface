package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.Role;
import com.lianhe.nine.intface.dao.RoleMapper;
import com.lianhe.nine.intface.service.IRoleService;
import com.lianhe.nine.intface.serviceImpl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}
