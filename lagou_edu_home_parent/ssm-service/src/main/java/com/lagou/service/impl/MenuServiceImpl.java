package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    /*
    查询所有菜单列表
     */
    @Override
    public List<Menu> findSubMenuListByPid(Integer id) {

        List<Menu> menuList = menuMapper.findSubMenuListByPid(id);

        return menuList;
    }

    /*
    查询所有的菜单信息
     */
    @Override
    public List<Menu> findAllMenu() {

        return menuMapper.findAllMenu();
    }
    /*
    根据id查询菜单信息 用于回显修改菜单信息的操作
     */

    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
