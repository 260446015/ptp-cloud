package com.yonyougov.gateway.service;

import com.yonyougov.gateway.entity.menu.ApMenu;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/7
 */
public interface IMenuService {
    ApMenu getMenu(String menuId);
}
