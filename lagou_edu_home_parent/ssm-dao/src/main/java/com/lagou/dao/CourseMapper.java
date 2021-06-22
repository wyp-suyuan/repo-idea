package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {


    /*
    多条件课程列表查询

    根据可课程名和状态进行查询  CourseName status
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);


    /*
    新增课程信息
     */

    public void saveCourse(Course course);

    /*
    新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /*
    回显课程信息
    根据id 查询课程信息 及关联的教师信息
     */
    public CourseVO findCourseById(Integer id);

    /*
    更新课程
     */
    public void updateCourse(Course course);

    /*
    更新讲师
     */
    public void updateTeacher(Teacher teacher);

    /*
    修改课程状态
     */
    public void updateCourseStatus(Course course);
}
