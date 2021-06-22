package com.lagou.service;

/*
菜单节点
 */

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
/*
菜单节点及父子级
 */
    public List<Menu> findSubMenuListByPid(Integer id);

    /*
    查询所有的菜单信息
     */
    public List<Menu> findAllMenu();
    /*
    根据id查询菜单信息  用于回显修改操作
     */
    public Menu findMenuById(Integer id);

}
