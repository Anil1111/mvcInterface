package com.nine.intface.testing.serviceImpl;

import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.BodyMapper;
import com.nine.intface.testing.po.Body;
import com.nine.intface.testing.service.IBodyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * body 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class BodyServiceImpl extends BaseServiceImpl<BodyMapper, Body> implements IBodyService {

}
