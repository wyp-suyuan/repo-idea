package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 资源模块
 */
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
    查询资源分类信息
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true,200,"查询资源分类信息相应成功",allResourceCategory);
    }
    /*
    添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        //根据是否携带id判断是添加还是修改操作
        if (resourceCategory.getId() == null){

            //没有携带id  添加操作
            resourceCategoryService.saveResourceCategory(resourceCategory);

            return  new ResponseResult(true,200,"添加资源分类成功",null);

        }else{

            //携带id  修改操作
            resourceCategoryService.updateResourceCategory(resourceCategory);

            return new ResponseResult(true,200,"修改资源分类成功",null);
        }
    }

    /*
    删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        resourceCategoryService.deleteResourceCategory(id);

        return new ResponseResult(true,200,"删除资源分类成功", null);
    }
}
