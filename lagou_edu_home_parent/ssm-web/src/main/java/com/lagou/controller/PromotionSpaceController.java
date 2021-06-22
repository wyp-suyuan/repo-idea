package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/PromotionSpace")
@RestController
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
    广告位列表查询
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> promotionSpace = promotionSpaceService.findPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "广告位查询成功", promotionSpace);

        return responseResult;
    }

    /*
     添加&修改广告位
     */

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        //判断是添加 还是 修改 取决于参数id是否有值
        if (promotionSpace.getId() == null){

            promotionSpaceService.savePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"添加广告位成功", null);
        }else {

            promotionSpaceService.updatePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"更新广告位成功", null);
        }

    }
    /*
    回显广告位信息
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "回显广告位名称成功", promotionSpace);

        return responseResult;
    }
}
