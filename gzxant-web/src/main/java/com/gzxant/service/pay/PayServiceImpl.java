package com.gzxant.service.pay;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gzxant.entity.order.EnrollOrder;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements IPayService {

    private static final String ORDER_NAME = "报名费支付";

    private static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Autowired
    private BestPayServiceImpl bestPayService;

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
}
