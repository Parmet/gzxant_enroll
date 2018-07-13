package com.gzxant.service.enroll.enter;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.dao.enroll.enter.EnrollEnterDao;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import com.gzxant.base.service.impl.BaseService;

/**
 * <p>
 * 参赛者信息 服务实现类
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnrollEnterService extends BaseService<EnrollEnterDao, EnrollEnter> implements IEnrollEnterService {
    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    @Override
    public EnrollEnter findbyIdEnterdate(String personnel_id) {
        EnrollEnter enrollEnter = selectOne(Condition.create().in("personnel_id",personnel_id));
        return enrollEnter;
    }
}
