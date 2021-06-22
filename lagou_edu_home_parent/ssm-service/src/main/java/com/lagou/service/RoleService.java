package com.lagou.service;

import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

/*
角色表
 */
public interface RoleService {

    /*
    角色查询&多条件查询
     */
    public List<Role> findAllRole(Role role);

    /*
    根据角色id查询出关联菜单
     */
    public List<String> findMenuByRoleId(Integer roleId);

    /*
    为角色分配菜单
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    /*
    根据id删除角色
     */
    public void deleteRole(Integer id);

    /*
    获取当前角色拥有的 资源信息
     */
    public ResponseResult findResourceListByRoleId(Integer id);

    /*
    为角色分配资源
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
