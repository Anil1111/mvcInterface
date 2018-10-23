package com.nine.intface.common.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nine.intface.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * @author : Rubi
 * @version : 2018-10-13 12:39
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements IBaseService<T> {
    /**
     * 只能查询类型 T 的分页数据
     * @param pageIndex
     * @param pageSize
     * @param queryWrapper
     * @return
     */
    @Override
    public PageInfo<T> getPageByHelper(int pageIndex, int pageSize, Wrapper<T> queryWrapper) {
        PageHelper.startPage(pageIndex,pageSize);
        List<T> list=baseMapper.selectList(queryWrapper);
        PageInfo pageInfo = new PageInfo(list);
        PageHelper.clearPage();

        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByHelperMaps(int pageIndex, int pageSize, Wrapper<T> queryWrapper) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        PageInfo pageInfo = new PageInfo(list);
        PageHelper.clearPage();
        return pageInfo;
    }
}
