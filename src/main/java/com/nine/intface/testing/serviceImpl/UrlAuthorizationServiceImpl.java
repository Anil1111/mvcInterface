package com.nine.intface.testing.serviceImpl;

import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlAuthorizationMapper;
import com.nine.intface.testing.po.UrlAuthorization;
import com.nine.intface.testing.service.IUrlAuthorizationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * url,authorization关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlAuthorizationServiceImpl extends BaseServiceImpl<UrlAuthorizationMapper, UrlAuthorization> implements IUrlAuthorizationService {

}
