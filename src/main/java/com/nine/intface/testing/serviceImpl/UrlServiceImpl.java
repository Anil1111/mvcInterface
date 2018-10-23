package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.UrlMapper;
import com.nine.intface.testing.po.Url;
import com.nine.intface.testing.service.IUrlService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class UrlServiceImpl extends BaseServiceImpl<UrlMapper, Url> implements IUrlService {

}
