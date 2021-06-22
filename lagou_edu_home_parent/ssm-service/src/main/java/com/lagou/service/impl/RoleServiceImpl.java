package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*
   角色查询&多条件查询
    */
    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {

        List<String> stringList = roleMapper.findMenuByRoleId(roleId);
        return stringList;
    }
    /*
    为角色分配菜单
     */

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        //1.清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2.插入数据
        Date date = new Date();
        Role_menu_relation role_menu_relation = new Role_menu_relation();
        role_menu_relation.setRoleId(roleMenuVo.getRoleId());
        for (Integer integer : roleMenuVo.getMenuIdList()) {

            role_menu_relation.setMenuId(integer);
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.RoleContextMenu(role_menu_relation);
        }

    }
    /*
    根根据id删除角色
     */

    @Override
    public void deleteRole(Integer id) {


        //1.清空角色和菜单的中间表信息
        roleMapper.deleteRoleContextMenu(id);

        //2.删除角色信息
        roleMapper.deleteRole(id);
    }
    /*
    获取当前角色拥有的 资源信息
     */

    @Override
    public ResponseResult findResourceListByRoleId(Integer id) {

        //先获取角色的资源分类信息
        List<ResourceCategory> resourceCategoryList = roleMapper.findAllResourceCategoryByRoleId(id);

        //再根据资源分类信息获取资源信息
        for (ResourceCategory resourceCategory : resourceCategoryList) {

            List<Resource> resourceList = roleMapper.findAllResourceByRole(resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
        }


        return new ResponseResult(true,200,"获取角色资源信息成功",resourceCategoryList);
    }
    /*
    为角色分配资源
     */

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //分配之前清空中间表
        roleMapper.deleteRoleContextMenu(roleResourceVo.getRoleId());

        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();

        //使用for循环进行插入操作 插入时 要补全信息
        for (Integer integer : roleResourceVo.getResourceIdList()) {

            roleResourceRelation.setResourceId(integer);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setCreatedTime(new Date());
            roleResourceRelation.setUpdatedTime(new Date());
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            roleMapper.roleContextResource(roleResourceRelation);
        }
    }
}
