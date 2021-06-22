package com.lagou.controller;


import com.lagou.domain.Test;
import com.lagou.service.TestService;
import com.lagou.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){

        List<Test> testList = testService.findAllTest();

        return testList;
    }
}
