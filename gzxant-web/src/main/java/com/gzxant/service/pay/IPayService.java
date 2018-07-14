package com.gzxant.service.pay;

import com.gzxant.entity.order.EnrollOrder;
import com.lly835.bestpay.model.PayResponse;

public interface IPayService {

    PayResponse create(EnrollOrder order);
}
