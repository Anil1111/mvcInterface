package com.nine.intface.testing.serviceImpl;


import com.nine.intface.common.serviceImpl.BaseServiceImpl;
import com.nine.intface.testing.dao.PreScriptMapper;
import com.nine.intface.testing.po.PreScript;
import com.nine.intface.testing.service.IPreScriptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * pre_script 表 服务实现类
 * </p>
 *
 * @author Rubi
 * @since 2018-10-23
 */
@Service
public class PreScriptServiceImpl extends BaseServiceImpl<PreScriptMapper, PreScript> implements IPreScriptService {

}
