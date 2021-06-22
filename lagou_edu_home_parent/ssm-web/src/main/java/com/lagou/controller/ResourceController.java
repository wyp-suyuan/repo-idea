package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
    资源信息分页&条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){

        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);

        return new ResponseResult(true,200,"资源信息分页&条件查询查询成功", pageInfo);
    }

    /*
    添加&更新资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        //根据是否携带id判断是添加还是更新操作
        if (resource.getId() == null){
            //添加操作
            resourceService.saveResource(resource);
            return new ResponseResult(true, 200,"添加资源信息成功", null);
        }else {
            //更新操作
            resourceService.updateResource(resource);
            return new ResponseResult(true, 200,"更新资源信息成功", null);
        }
    }
}
