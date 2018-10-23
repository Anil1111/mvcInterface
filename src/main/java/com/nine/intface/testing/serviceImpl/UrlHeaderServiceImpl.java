package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlHeaderMapper;
import com.nine.intface.testing.po.UrlHeader;
import com.nine.intface.testing.service.IUrlHeaderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * url,header关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlHeaderServiceImpl extends BaseServiceImpl<UrlHeaderMapper, UrlHeader> implements IUrlHeaderService {

}
