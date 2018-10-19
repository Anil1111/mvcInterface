package com.lianhe.nine.intface.serviceImpl;

import com.lianhe.nine.intface.po.Permission;
import com.lianhe.nine.intface.dao.PermissionMapper;
import com.lianhe.nine.intface.service.IPermissionService;
import com.lianhe.nine.intface.serviceImpl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
