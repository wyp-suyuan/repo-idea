package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /*
    广告分页查询
     */
    public PageInfo findAllAdByPage(PromotionAdVO promotionAdVO);
    /*
    动态管理广告的上下线
     */
    public void updatePromotionAdStatus(Integer id, Integer status);
}
