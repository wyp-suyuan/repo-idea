package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

/*
用户模块
 */
public interface UserMapper {

    /*
    分页查询及多条件查询
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /*
    用户状态设置
     */
    public void updateUserStatus(User user);
    /*
    用户登录
     */
    public User login(User user);

    /*
    根据用户ID清空中间表
    */
    public void deleteUserContextRole(Integer userId);
    /*
    分配角色
    */
    void userContextRole(User_Role_relation user_role_relation);


    //为角色动态获取菜单资源信息
    /*
      1.根据用户ID查询关联角色
     */
    public List<Role> findUserRelationRoleById(int id);

    /*
      2.根据用户的角色ID查询顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
     3.根据用户的角色ID查询顶级菜单关联的子菜单
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
     4.获取用户拥有的资源权限信息 （本质还是用角色进行获取）
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
