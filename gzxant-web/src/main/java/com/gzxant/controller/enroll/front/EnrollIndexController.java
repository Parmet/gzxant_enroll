package com.gzxant.controller.enroll.front;

import com.gzxant.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/front")
public class EnrollIndexController extends BaseController {

    @GetMapping({"/{action}", "/{action}/{phone}"})
    public String action(@PathVariable("action") String action,
                         @PathVariable(name = "phone", required = false) String phone,
                         @RequestParam(name = "openid", required = false) String openid,
                         Model model) {
        model.addAttribute("openid", openid);
        model.addAttribute("phone", phone);
        return "/enroll/front/" + action;
    }
}