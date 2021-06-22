package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVO promotionAdVO) {

        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());

        List<PromotionAd> promotionAdList = promotionAdMapper.findAllAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);

        return pageInfo;
    }

    /*
    动态管理广告的上下线
     */
    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {

        //1.补全信息
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        //2.调用dao
        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }
}
