package com.gzxant.service.personnel;

import com.gzxant.base.service.IBaseService;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;

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
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    EnrollPersonnel login(String name, String password);

    /**
     * 用户报名
     * @param param
     */
    boolean insertBean(EnrollPersonnel param);
    /**
     * 获取所有数据
     */
    int selectAllcount();
    /**
     * 检测用户是否存在
     */
    public Boolean checknumbers(String numbers);

    boolean checkPhone(String id, String phone);
}
