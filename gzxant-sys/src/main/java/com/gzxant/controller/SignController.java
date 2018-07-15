package com.gzxant.controller;

import com.gzxant.base.controller.BaseController;
import com.gzxant.common.entity.config.SysConfig;
import com.gzxant.common.service.config.ISysConfigService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chen on 2017/7/14.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统注册-登录-登出
 */
@Controller
public class SignController extends BaseController{
	
	@Autowired
	private ISysConfigService configService;
	
	private List<SysConfig> configs;

	/**
     *  空地址请求
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("base", request.getContextPath());
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? "redirect:index" : getConfig("LOGIN_URL");
    }


    /**
     * index 界面
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return getConfig("INDEX_URL");
    }


    /**
     * 登录 界面
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("base", request.getContextPath());
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? "redirect:index" : getConfig("LOGIN_URL");
    }


    /**
     * 登录出错界面--实际的登录 交给了 shiro
     * @param request
     * @param userName
     * @param model
     * @return
     */
    @PostMapping(value = "login")
    public String fail(HttpServletRequest request,@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        String error=(String)request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(error!=null){
            if(error.contains("DisabledAccountException")){
                model.addAttribute("error","用户已被屏蔽,请登录其他用户.");
            }else if(error.contains("UnknownAccountException")){
                model.addAttribute("error","用户不存在,请检查后重试!");
            }else{
                model.addAttribute("error","登录失败，请重试.");
            }
        }
        
        model.addAttribute("base", request.getContextPath());
        return getConfig("LOGIN_URL");
    }

    public String getConfig(String code) {
    	if (configs == null || configs.isEmpty()) {
    		configs = configService.getSub("SYS_LOGIN");
    	}
    	
    	for (SysConfig config : configs) {
			if (config.getJkey().equals(code)) {
				return config.getValue();
			}
		}
    	
		return "";
    }
}
