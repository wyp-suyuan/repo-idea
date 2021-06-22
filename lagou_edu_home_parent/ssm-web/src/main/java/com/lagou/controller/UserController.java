package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
用户模块
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    用户多条件查询及分页查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "用户多条件查询及分页查询成功", pageInfo);

        return responseResult;
    }

    /*
    用户状态设置
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status){

        userService.updateUserStatus(id, status);

        return new ResponseResult(true,200,"用户状态更新成功",status);
    }
    /*
    用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);

        if (login != null){

            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token",access_token);
            map.put("user_id",login.getId());

            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",login.getId());

            //前台有时候也需要用户的信息
            //将信息发送给前台
            map.put("user",login);

            return new ResponseResult(true,1,"响应成功",map);
        }else {
            return new ResponseResult(true,1,"用户名密码错误",null);
        }
    }
    /*
    分配角色(回显)
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){

        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",roleList);

    }

    /*
    分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功",null);

    }
    /*
    为角色动态获取菜单
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //获取请求头中的token
        String token = request.getHeader("Authorization");
        System.out.println("token:" + token);
        //获取session中的token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        System.out.println("access_token: " + access_token);

        if (token.equals(access_token)){

            Integer user_id = (Integer) session.getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }
        else {
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;

        }

    }
}
