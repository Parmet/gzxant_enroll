package com.gzxant.controller.enroll.front;

import com.gzxant.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class EnrollIndexController extends BaseController {

    @GetMapping({"/{action}", "/{action}/{id}"})
    public String action(@PathVariable("action") String action,
                         @PathVariable(name = "id", required = false) String id,
                         Model model) {
        model.addAttribute("id", id);
        return "/enroll/front/" + action;
    }
}