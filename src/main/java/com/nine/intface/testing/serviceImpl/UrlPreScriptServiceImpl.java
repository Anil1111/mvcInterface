package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlPreScriptMapper;
import com.nine.intface.testing.po.UrlPreScript;
import com.nine.intface.testing.service.IUrlPreScriptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * url,pre_script关系表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlPreScriptServiceImpl extends BaseServiceImpl<UrlPreScriptMapper, UrlPreScript> implements IUrlPreScriptService {

}
