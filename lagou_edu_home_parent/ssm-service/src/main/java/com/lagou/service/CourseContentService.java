package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {


    /*
    根据课程id查询章节信息及课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
    根据id查询课程信息
     */
    public Course findCourseByCourseId(Integer courseId);
    /*
    保存章节信息
     */
    public void saveSection(CourseSection courseSection);
    /*
    更新章节信息
     */
    public void updateSection(CourseSection courseSection);
    /*
    修改章节状态
     */
    public void updateSectionStatus(Integer id, Integer status);
    /*
    新建课时信息
     */
    public void saveLesson(CourseLesson courseLesson);
}
