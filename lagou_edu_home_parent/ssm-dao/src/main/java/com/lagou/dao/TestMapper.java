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
    public void  gitPull1();
    public void  gitPull2();
    public void  gitPull3();
    public void  gitPull4();
    public void  gitPull5();

    public void  gitPull7();
    public void  gitPull8();
    public void  gitPull9();
    public void  gitPull10();
}
