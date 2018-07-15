package com.gzxant.service.enroll.personnel;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.base.service.impl.BaseService;
import com.gzxant.dao.enroll.personnel.EnrollPersonnelDao;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class EnrollPersonnelService extends BaseService<EnrollPersonnelDao, EnrollPersonnel> implements IEnrollPersonnelService {
    /**
     * 检测登录名是否重复
     *
     * @param name
     * @param id
     * @return
     */
    @Override
    public Boolean checkLoginName(String name, Long id) {
        EnrollPersonnel enrollPersonnel = selectOne(Condition.create().eq("phone", name));
        return enrollPersonnel == null || !id.equals(0L) && enrollPersonnel.getId().equals(id);
    }
    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    @Override
    public EnrollPersonnel login(String name, String password) {
        EnrollPersonnel enrollPersonnel = selectOne(Condition.create().eq("phone", name).in("password",password));
        return enrollPersonnel;
    }
}
