package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /*
     资源信息分页&条件查询
     */
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResource(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        return pageInfo;
    }

    /*
    添加资源信息
     */
    @Override
    public void saveResource(Resource resource) {

        //1.补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        //2.调用dao
        resourceMapper.saveResource(resource);
    }

    /*
    更新资源信息
     */
    @Override
    public void updateResource(Resource resource) {

        //1.补全信息
        Date date = new Date();
        resource.setUpdatedTime(date);

        //2.调用dao
        resourceMapper.updateResource(resource);
    }
}
