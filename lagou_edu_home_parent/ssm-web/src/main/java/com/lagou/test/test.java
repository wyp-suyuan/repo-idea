package com.lagou.test;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {

    @Autowired
    private CourseService courseService;

    @Test
    public void test(){

        CourseVO courseVO = new CourseVO();
        courseVO.setCourseName("高手");
        courseVO.setStatus(0);
        System.out.println(courseVO);
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        for (Course course : courseList) {
            System.out.println(course);
        }
    }
}
