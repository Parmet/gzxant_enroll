package com.gzxant.service.enroll.personnel;

import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.base.service.IBaseService;

/**
 * <p>
 * 参赛者信息 服务类
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
public interface IEnrollPersonnelService extends IBaseService<EnrollPersonnel> {
    /**
     * 检测登录名是否重复
     *
     * @param name
     * @param id
     * @return
     */
    Boolean checkLoginName(String name, Long id) ;

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    EnrollPersonnel login(String name, String password);
}
