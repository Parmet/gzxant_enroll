package com.gzxant.controller.enroll.pay;


import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.entity.order.EnrollOrder;
import com.gzxant.service.order.IEnrollOrderService;
import com.gzxant.service.pay.IPayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class PayController {

    @Autowired
    private IPayService payService;

    @Autowired
    private IEnrollOrderService orderService;


    @RequestMapping("/pay")
    public String create(Model model, @RequestParam(value = "returnUrl",required = false) String returnUrl,String openid) {
        EnrollOrder order = orderService.selectOne(Condition.create().eq("openid",openid));
        if (order == null){
            throw new SecurityException("没有该订单");
        }

        PayResponse payResponse = payService.create(order);
        model.addAttribute("payResponse", payResponse);
        model.addAttribute("returnUrl", returnUrl);
        return "/pay/pay";
    }

}
