package com.gzxant.base.controller;

import com.alibaba.fastjson.JSON;
import com.gzxant.base.service.ISysConfigService;
import com.gzxant.base.vo.PCAjaxVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycxiao
 * @since 2018-05-14
 */
@Controller
@RequestMapping(value = "/sys/config")
public class SysConfigController extends BaseController {
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 进入系统字典的主页
     *
     * @return
     */
    @RequiresPermissions("sys:config:list")
    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("configTrees", JSON.toJSON(sysConfigService.getConfigTree()).toString());
        return "config/list";
    }



    /**
     * 保存数据
     *
     * @param dicKey;//字典名称
     * @param dicValue;//字典值
     * @param dicPid;//上级ID
     * @param desc;//字典描述
     * @param sort;//排序
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/insert")
    public String insert(Long id, String dicKey, String dicValue, Long dicPid, String type, String desc,
                         String sort, String path, String invalid, String value, RedirectAttributes redirectAttributes) {

        if (null != id) {
            logger.info("更新字典 id={}", id);
            boolean success = sysConfigService.update(id, dicKey, dicValue, type, desc, sort, invalid, value);
            redirectAttributes.addFlashAttribute("message", "更新配置成功");

        } else {
        	sysConfigService.insert(dicKey, dicValue, dicPid, type, desc, sort, invalid, path, value);
            redirectAttributes.addFlashAttribute("message", "保存配置成功");
        }

        return "redirect:/sys/config";
    }

    /**
     * 获取资源
     *
     * @param id 资源ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("config", sysConfigService.selectById(id));
        return map;
    }

    /**
     * 删除节点已经子节点
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public PCAjaxVO delete(@PathVariable("id") Long id) {
        return sysConfigService.delete(id);
    }
}
