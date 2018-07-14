package com.gzxant.service.order;

import com.gzxant.base.service.impl.BaseService;
import com.gzxant.dao.order.EnrollOrderDao;
import com.gzxant.entity.order.EnrollOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tecty
 * @since 2018-07-14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnrollOrderService extends BaseService<EnrollOrderDao, EnrollOrder> implements IEnrollOrderService {

    @Override
    public EnrollOrder findOrderByOpenId(String openid) {
        return null;
    }
}
