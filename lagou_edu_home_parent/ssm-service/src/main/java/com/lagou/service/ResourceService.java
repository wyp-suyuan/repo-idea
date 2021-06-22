package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

/*
资源模块
 */
public interface ResourceService {
    /*
    资源信息分页&条件查询
     */
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    /*
    添加资源信息
     */
    public void saveResource(Resource resource);
    /*
    更新资源信息
     */
    public void updateResource(Resource resource);
}
