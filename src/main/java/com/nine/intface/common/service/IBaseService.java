package com.nine.intface.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-12 19:41 下午
 */
@Transactional(rollbackFor = Exception.class)
public interface IBaseService<T> extends IService<T> {

    /**
     *
     * @param pageIndex
     * @param pageSize
     * @param queryWrapper
     * @return
     */
    PageInfo<T> getPageByHelper(int pageIndex, int pageSize, Wrapper<T> queryWrapper);
    /**
     * <p>
     * 翻页查询
     * </p>
     *
     * @param
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    PageInfo<Map<String, Object>> getPageByHelperMaps(int pageIndex,int pageSize, Wrapper<T> queryWrapper);
}
