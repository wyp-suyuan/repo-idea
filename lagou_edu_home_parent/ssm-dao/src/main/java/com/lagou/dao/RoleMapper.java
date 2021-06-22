package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

/*
角色表
 */
public interface RoleMapper {
    /*
    查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);
    /*
    根据角色ID查询菜单信息
    */
   public List<String> findMenuByRoleId(Integer roleId);

   /*
   为角色分配菜单列表
   根据roleId清空中间表
    */
   public void deleteRoleContextMenu(Integer rId);


   /*
   为角色分配菜单列表
   向中间表插入数据
    */
   public void RoleContextMenu(Role_menu_relation role_menu_relation);

   /*
   根据id删除角色
    */
   public void deleteRole(Integer id);

   /*
   获取当前角色拥有的 资源信息
    */
   public List<Resource> findAllResourceByRole(Integer id);

   /*
   获取当前角色拥有的 资源分类信息
    */
   public List<ResourceCategory> findAllResourceCategoryByRoleId(Integer id);

   /*
   清空分配角色资源表
    */
   public void deleteRoleContextResource(Integer id);

   /*
   为角色分配资源  向角色和资源表中插入数据
    */
   public void roleContextResource(RoleResourceRelation roleResourceRelation);
}
