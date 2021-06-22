package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){

        PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", allAdByPage);

        return responseResult;
    }
    /*
    图片上传功能的实现
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空 如果为空时，直接抛出异常；不为空时继续执行
        if (file.isEmpty()) {
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
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }

        //8.上传至服务器
        file.transferTo(filePath);

        //9.图片回显 使用Map进行保存数据
        Map<String, String> map = new HashMap<>();

        map.put("fileName", newFileName);

        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);


        return responseResult;
    }
    /*
    动态管理广告的上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status){

        promotionAdService.updatePromotionAdStatus(id, status);

        Map<String, Integer> map = new HashMap<>();

        map.put("status",status);

        return new ResponseResult(true,200,"动态上下线成功",map);
    }
}
