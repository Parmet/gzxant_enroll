package com.gzxant.service.order;

import com.gzxant.base.service.IBaseService;
import com.gzxant.entity.order.EnrollOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tecty
 * @since 2018-07-14
 */
public interface IEnrollOrderService extends IBaseService<EnrollOrder> {

    EnrollOrder findOrderByOpenId(String openid);
}
