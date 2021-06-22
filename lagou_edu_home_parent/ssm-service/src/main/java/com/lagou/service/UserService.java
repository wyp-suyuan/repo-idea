package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

/*
用户模块
 */
public interface UserService {

    /*
    用户分页查询及多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);
    /*
    用户状态设置
     */
    public void updateUserStatus(Integer id, String status);
    /*
    用户登录
     */
    public User login(User user) throws Exception;
    /**
     * 获取用户拥有的角色
     * */
    public List<Role> findUserRelationRoleById(int id) ;

    /*
    用户关联角色
    */
    public void userContextRole(UserVo userVo);
    /*
    为用户分配角色
     */
    public ResponseResult getUserPermissions(Integer id);
}
