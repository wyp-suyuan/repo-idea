package com.lagou.service.impl;


import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findPromotionSpace() {

        return promotionSpaceMapper.findAllPromotionSpace();
    }

    /*
    新建广告位信息
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //1.补全信息
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());

        //2.调用dao
        promotionSpaceMapper.savePromotionSpace(promotionSpace);

    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        //1.补全信息
        Date date = new Date();
        promotionSpace.setUpdateTime(date);

        //2.调用dao
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }
}
