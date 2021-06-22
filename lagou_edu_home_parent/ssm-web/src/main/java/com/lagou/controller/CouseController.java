package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/course")
@RestController //相当于 添加了 @ResponseBody 和 @Controller  这两个注解 @ResponseBody注解 是用于给前台发送json格式数据用的
public class CouseController {

    @Autowired
    private CourseService courseService;

    /*
    多条件查询课程
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        List<Course> courseList = courseService.findCourseByCondition(courseVO);



        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);

        return responseResult;
    }

    /*
    课程图片上传
     */

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空 如果为空时，直接抛出异常；不为空时继续执行
        if (file.isEmpty()){
            throw new RuntimeException();
        }


        //2.获取项目路径
        //获取到的路径信息： D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");

        //想做的时获取ssm-web之前的路径信息
        String webappPath = realPath.substring(0, realPath.indexOf("ssm_web"));


        //3.获取原文件名
        String filename = file.getOriginalFilename();

        //4.生成新的文件名
        String newFileName = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //5.获取文件上传路径信息
        String uploadPath = webappPath + "upload\\";

        //6.将源文件和目录拼接在一起
        File filePath = new File(uploadPath, newFileName);

        //7.判断父目录是否存在 不存在就创建
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }

        //8.上传至服务器
        file.transferTo(filePath);

        //9.图片回显 使用Map进行保存数据
        Map<String, String> map = new HashMap<>();

        map.put("fileName",newFileName);

        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);


        return responseResult;
    }

    /*
    保存课程信息和教师信息
     */

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        /*
        //新增操作
        courseService.saveCourseOrTeacher(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;*/

        //更新和保存信息进行汇总
        if(courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }

    }

   /*
    回显课程信息
    根据id查询课程信息 及关联的讲师信息
     */

   @RequestMapping("/findCourseById")
   public ResponseResult findCourseById(@RequestParam int id){

       CourseVO courseVO = courseService.findCourseById(id);

       ResponseResult responseResult = new ResponseResult(true,200,"查询成功",courseVO);

       return responseResult;
   }

   @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){

       courseService.updateCourseStatus(id, status);

       Map<String,Integer> map = new HashMap<>();

       map.put("status",status);

       return new ResponseResult(true,200,"状态修改成功",map);
   }

}
