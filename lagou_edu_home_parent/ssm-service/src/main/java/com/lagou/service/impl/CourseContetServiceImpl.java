package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContetServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    /*
    根据课程id查询章节信息及课时信息
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        System.out.println("service:" + sectionList);
        return sectionList;
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {

        return courseContentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        //1.补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //2.调用dao层保存信息
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //1.补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);

        //2.调用dao进行更新操作
        courseContentMapper.updateSection(courseSection);

    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {

        //1.补全信息
        CourseSection courseSection = new CourseSection();
        Date date = new Date();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(date);

        //2.调用dao进行信息更新
        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //1.补全信息
        Date date = new Date();
        courseLesson.setUpdateTime(date);
        courseLesson.setCreateTime(date);
        System.out.println("service1"+ courseLesson);


        //2.调用dao层保存课时信息
        courseContentMapper.saveLesson(courseLesson);
        System.out.println("service2"+ courseLesson);
    }
}
