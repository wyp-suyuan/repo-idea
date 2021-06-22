package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

/*
资源模块
 */
public interface ResourceMapper {

    /*
    资源信息分页&条件查询
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);

    /*
    添加资源信息
     */
    public void saveResource(Resource resource);

    /*
    更新资源信息
     */
    public void updateResource(Resource resource);
    /*
    根据资源分类信息 删除资源信息
     */
    public void deleteResourceByResourceCategoryID(Integer id);
}
