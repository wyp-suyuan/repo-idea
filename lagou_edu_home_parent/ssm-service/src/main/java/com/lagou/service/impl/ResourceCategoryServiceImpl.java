package com.lagou.service.impl;


import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Autowired
    private ResourceMapper resourceMapper;
    /*
    查询资源分类信息
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();
        return allResourceCategory;
    }
    /*
    添加资源分类
     */

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        //2.调用dao
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }
    /*
    修改资源分类
     */

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //1.补全信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("yasuo");

        //2.调用dao
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }
    /*
    删除资源分类信息
     */

    @Override
    public void deleteResourceCategory(Integer id) {

        //资源分类信息 和资源信息关联 如果删除 资源分类信息  首先要清空资源表中和资源分类相关的 数据
        resourceMapper.deleteResourceByResourceCategoryID(id);
        //调用dao层 删除资源分类
        resourceCategoryMapper.deleteResourceCategory(id);

    }
}
