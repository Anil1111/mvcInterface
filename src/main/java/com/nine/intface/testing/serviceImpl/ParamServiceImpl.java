package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.ParamMapper;
import com.nine.intface.testing.po.Param;
import com.nine.intface.testing.service.IParamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * param 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class ParamServiceImpl extends BaseServiceImpl<ParamMapper, Param> implements IParamService {

}
