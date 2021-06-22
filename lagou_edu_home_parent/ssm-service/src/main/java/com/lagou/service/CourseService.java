package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
    多条件查询课程信息
    查询条件：课程名 和 状态 courseName status
     */

    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
    保存课程信息及讲师信息
    前台传递的信息有 课程信息和讲师信息  统一封装在courseVO中
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    回显课程信息
    根据id查询课程信息 及关联的讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /*
    更新课程信息和讲师信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    更新课程状态信息
     */
    public void updateCourseStatus(Integer id, Integer status);
}
