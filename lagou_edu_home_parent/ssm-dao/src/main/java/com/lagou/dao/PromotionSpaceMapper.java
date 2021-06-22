package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/*
广告位模块
 */
public interface PromotionSpaceMapper {

    /*
    广告位列表查询
     */
    public List<PromotionSpace> findAllPromotionSpace();
    /*
    添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);
    /*
    修改广告位信息
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
    /*
    回显广告位名称
     */
    public PromotionSpace findPromotionSpaceById(Integer id);
}
