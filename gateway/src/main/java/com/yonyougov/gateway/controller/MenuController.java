package com.yonyougov.gateway.controller;

import com.yonyougov.gateway.entity.menu.ApMenu;
import com.yonyougov.gateway.service.IMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/7
 */
@RestController
@RequestMapping("api/menu")
public class MenuController  {

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("{menuId}")
    public ResponseEntity getMenu(@PathVariable String menuId){
        ApMenu apMenu = menuService.getMenu(menuId);
        return ResponseEntity.ok(apMenu);
    }
}
