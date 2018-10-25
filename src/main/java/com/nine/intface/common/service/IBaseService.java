package com.nine.intface.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Rubi
 * @version : 2018-10-12 19:41 下午
 */
@Transactional(rollbackFor = Exception.class)
public interface IBaseService<T> extends IService<T> {


}
