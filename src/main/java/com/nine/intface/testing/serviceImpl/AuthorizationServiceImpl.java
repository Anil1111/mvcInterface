package com.nine.intface.testing.serviceImpl;

import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.AuthorizationMapper;
import com.nine.intface.testing.po.Authorization;
import com.nine.intface.testing.service.IAuthorizationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * authorization 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class AuthorizationServiceImpl extends BaseServiceImpl<AuthorizationMapper, Authorization> implements IAuthorizationService {

}
