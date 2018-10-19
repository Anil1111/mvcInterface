package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.Permission;
import com.lianhe.nine.intface.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Transactional(rollbackFor = Exception.class)
public interface IPermissionService extends IBaseService<Permission> {

}
