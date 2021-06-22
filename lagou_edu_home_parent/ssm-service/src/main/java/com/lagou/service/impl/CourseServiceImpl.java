package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    //注入dao层的对象
    @Autowired
    private CourseMapper courseMapper;

    /*
   多条件查询课程信息
   查询条件：课程名 和 状态 courseName status
    */
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        return courseMapper.findCourseByCondition(courseVO);

    }

    /*
   保存课程信息及讲师信息
   前台传递的信息有 课程信息和讲师信息  统一封装在courseVO中
    */
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {


        //保存课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程信息
        courseMapper.saveCourse(course);

        //获取新建课程的id
        int id = course.getId();

        //保存教师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补全教师信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);

        //保存教师信息
        courseMapper.saveTeacher(teacher);


    }


    /*
     回显课程信息
     根据id查询课程信息 及关联的讲师信息
      */
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    /*
    更新课程信息和讲师信息
     */

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //将传递过来的courseVO进行拆分
        //拆分出来课程信息
        Course course = new Course();

        //更新时间
        Date date = new Date();

        //补全更新信息
        course.setUpdateTime(date);

        //拆分出来
        BeanUtils.copyProperties(course, courseVO);

        //调用dao进行保存课程信息
        courseMapper.updateCourse(course);

        //拆分教师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补全信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());

        //调用dao保存教师信息
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {

        //封装从前台传递的数据 以及 补全更新时间
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //调用dao层传递course
        courseMapper.updateCourseStatus(course);
    }
}
