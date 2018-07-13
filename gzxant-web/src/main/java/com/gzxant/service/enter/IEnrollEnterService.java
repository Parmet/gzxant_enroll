package com.gzxant.service.enroll.enter;

import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.base.service.IBaseService;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 参赛者信息 服务类
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
public interface IEnrollEnterService extends IBaseService<EnrollEnter> {
    /**
     * 用户登录
     * @param personnel_id
     * @return
     */
    EnrollEnter findbyIdEnterdate(String personnel_id);
}
