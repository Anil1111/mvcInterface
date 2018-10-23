package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlBodyMapper;
import com.nine.intface.testing.po.UrlBody;
import com.nine.intface.testing.service.IUrlBodyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * url,body关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlBodyServiceImpl extends BaseServiceImpl<UrlBodyMapper, UrlBody> implements IUrlBodyService {

}
