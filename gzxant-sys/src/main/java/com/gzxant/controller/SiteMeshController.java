package com.gzxant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gzxant.base.controller.BaseController;
import com.gzxant.service.ISysMenuService;
import com.gzxant.shiro.GzxantSysUser;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:SiteMesh 操作
 */
@Controller
public class SiteMeshController extends BaseController{

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询系统用户 侧边栏菜单
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "layouts")
    public String getIndex(Model model, HttpServletRequest request) {

        model.addAttribute("gzxant", GzxantSysUser.ShiroUser());
        model.addAttribute("menus", sysMenuService.CaseMenu(GzxantSysUser.id()));

        return "layouts/default";
    }

    @PostMapping(value = "layouts")
    public String postIndex(Model model, HttpServletRequest request) {
        return getIndex( model,  request);
    }
}
