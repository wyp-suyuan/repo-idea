package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
    用户多条件查询及分页查询
     */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        return pageInfo;
    }

    /*
    用户状态设置
     */
    @Override
    public void updateUserStatus(Integer id, String status) {

        //1.补全信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        //2.调用dao
        userMapper.updateUserStatus(user);
    }

    /*
    用户登录
     */
    @Override
    public User login(User user) throws Exception {

        //1.调用mapper方法拿到查询结果
        User login = userMapper.login(user);

        //2.判断是否为空
        if (user != null && Md5.verify(user.getPassword(), Md5.md5key, login.getPassword())) {


            return login;
        } else {

            return null;
        }
    }
    /*
    根据id查询用户的角色信息
     */

    @Override
    public List<Role> findUserRelationRoleById(int id) {

        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        return roleList;
    }
    /*
    为用户分配角色
     */

    @Override
    public void userContextRole(UserVo userVo) {
        // 根据用户ID清空中间表的关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
// 向中间表添加记录
        for (Integer roleid : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }
    /*
    为角色动态获取菜单
     */

    @Override
    public ResponseResult getUserPermissions(Integer id) {

        //1.根据用户id查询相关的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //1.1把角色id提取出来放进一个idList中
        List<Integer> idList = new ArrayList<>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }

        //2.根据用户的角色id查询相关的顶级菜单
        List<Menu> menuList = userMapper.findParentMenuByRoleId(idList);

        //3.根据顶级菜单查询关联的子菜单
        //把查询的子级菜单放入menuList的subMenuList中
        for (Menu menu : menuList) {
            List<Menu> list = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(list);
        }

        //4.根据用户的角色id查询相关的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(idList);

        //5.准备一个集合把menuList和resourceList放入其中
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", menuList); //菜单信息
        map.put("resourceList",resourceList); //资源信息

        ResponseResult responseResult = new ResponseResult(true, 1, "为角色动态分配菜单成功", map);
        return responseResult;
    }
}
