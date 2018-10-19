package com.lianhe.nine.intface.service;

import com.lianhe.nine.intface.po.UserRole;
import com.lianhe.nine.intface.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-17
 */
@Transactional(rollbackFor = Exception.class)
public interface IUserRoleService extends IBaseService<UserRole> {

}
