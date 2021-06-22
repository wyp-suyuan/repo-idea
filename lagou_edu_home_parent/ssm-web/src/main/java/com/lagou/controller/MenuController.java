package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
    查询所有的菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = menuService.findAllMenu();

        return new ResponseResult(true,200,"查询所有菜单信息成功",menuList);

    }
    /*
    查询菜单信息(回显)
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //判断是否是新建菜单信息
        if (id == -1){

            List<Menu> menuList = menuService.findSubMenuListByPid(id);

            Map<String, Object> map = new HashMap<>();

            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"新建菜单回显成功",map);
        }else {

            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"修改菜单回显成功",map);
        }
    }
}
