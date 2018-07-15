package com.gzxant.service.pay;


import com.baomidou.mybatisplus.mapper.Condition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gzxant.entity.order.EnrollOrder;
import com.gzxant.service.order.IEnrollOrderService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements IPayService {

    private static final String ORDER_NAME = "报名费支付";
    private static final Double MONEY_RANGE = 0.01;

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private IEnrollOrderService orderService;

    public PayResponse create(EnrollOrder order) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(order.getOpenid()); //用户openid
        payRequest.setOrderId(String.valueOf(order.getId())); //订单id
        payRequest.setOrderAmount(order.getMoney().doubleValue()); //金额
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderName(ORDER_NAME);

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info(toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //验证签名
        //支付状态
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);

        EnrollOrder order = orderService.selectById(payResponse.getOrderId());
        if (order == null) {
            throw new SecurityException("订单不存在");
        }
        //支付金额
        if (!equals(payResponse.getOrderAmount(), order.getMoney().doubleValue())) {
            throw new SecurityException("支付金额不一致");
        }
        //todo 修改订单状态

        return payResponse;
    }


    private static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private static boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        }
        return false;
    }
}
