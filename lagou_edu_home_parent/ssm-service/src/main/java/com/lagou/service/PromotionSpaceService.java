package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/*
广告位模块
 */
public interface PromotionSpaceService {

    /*
    查询广告位列表信息（所有信息）
     */
    public List<PromotionSpace> findPromotionSpace();
    /*
    保存新建广告信息
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);
    /*
    更新广告信息
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
    /*
    回显广告位信息
     */
    public PromotionSpace findPromotionSpaceById(Integer id);
}
