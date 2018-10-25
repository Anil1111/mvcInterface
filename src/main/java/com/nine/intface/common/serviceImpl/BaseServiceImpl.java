package com.nine.intface.common.serviceImpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.intface.common.service.IBaseService;

/**
 * @author : Rubi
 * @version : 2018-10-13 12:39
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements IBaseService<T> {

}
