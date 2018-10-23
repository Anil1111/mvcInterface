package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlParamMapper;
import com.nine.intface.testing.po.UrlParam;
import com.nine.intface.testing.service.IUrlParamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * url,param关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlParamServiceImpl extends BaseServiceImpl<UrlParamMapper, UrlParam> implements IUrlParamService {

}
