package com.lagou.controller;


import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/courseContent")
@RestController
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /*
      根据id查询章节信息及课时信息
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        List<CourseSection> sectionAndLessonByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true, 200, "相应成功", sectionAndLessonByCourseId);

    }

    /*
    根据课程id课程信息
     */

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        return new ResponseResult(true,200, "响应成功",courseContentService.findCourseByCourseId(courseId));
    }
    /*

     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        //判断新增还是修改章节信息取决于传递的参数中是否含有章节id 即 id 字段
        if (courseSection.getId() == null){

            //如果传递的数据中id为空，则说明是新建课程章节
            courseContentService.saveSection(courseSection);

            return new ResponseResult(true,200,"新建章节成功",null);

        }else {

            //如果传递的数据id不为空，则说明是更新章节信息
            courseContentService.saveSection(courseSection);

            return  new ResponseResult(true,200,"更新章节成功",null);
        }

    }

    /*
    修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status){

        courseContentService.updateSectionStatus(id, status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true,200,"章节状态修改成功", map);

    }

    /*
    新建课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){

        System.out.println("controller"+courseLesson);
        courseContentService.saveLesson(courseLesson);

        return new ResponseResult(true,200,"新建课时信息成功",null);

    }

}
