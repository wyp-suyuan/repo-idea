package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

/*
菜单节点
 */
public interface MenuMapper {

    /*
    查询所有的菜单节点 及 父子级关系
     */
    public List<Menu> findSubMenuListByPid(int id);

    /*
    权限管理（菜单模块）
    查询所有的菜单信息
     */
    public List<Menu> findAllMenu();

    /*
    根据id回显菜单信息
     */
    public Menu findMenuById(Integer id);

}
