package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/*
角色表
 */
@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*
    角色信息查询&分页查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> roleList = roleService.findAllRole(role);

        return new ResponseResult(true,200,"角色信息查询成功",roleList);

    }

    /*
    查询所有的父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        HashMap<String, Object> map = new HashMap<>();

        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"分配菜单查询所有父子菜单信息成功",map);

    }

    /*
    根据角色id查询关联菜单id
     */
    @RequestMapping("/findMenuByRoleId")
    public  ResponseResult findMenuByRoleI(Integer roleId){

        List<String> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据id查询关联菜单id成功",menuByRoleId);

    }
    /*
    为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.RoleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"为角色分配菜单成功","");
    }

    /*
    根据id删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据id删除角色信息成功", "");

        return responseResult;
    }
    /*
    获取当前角色拥有的 资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer id){

        ResponseResult responseResult = roleService.findResourceListByRoleId(id);
        return responseResult;
    }

    /*
    为角色分配资源
    思路分析：分配资源前要将原有的资源信息进行清空  原有的资源信息就是中间表信息
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){

        roleService.roleContextResource(roleResourceVo);

        return new ResponseResult(true,200,"为角色分配资源成功",null);

    }
}
