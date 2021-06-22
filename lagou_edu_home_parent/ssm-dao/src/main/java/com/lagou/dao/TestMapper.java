package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

public interface TestMapper {

    /*
    对test表进行查询
     */
    public List<Test> findAllTest();
    
    /*
    测试git的拉取操作
    */
    public void  gitPull();
}
