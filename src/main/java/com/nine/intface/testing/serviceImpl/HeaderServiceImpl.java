package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.HeaderMapper;
import com.nine.intface.testing.po.Header;
import com.nine.intface.testing.service.IHeaderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * header 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class HeaderServiceImpl extends BaseServiceImpl<HeaderMapper, Header> implements IHeaderService {

}
