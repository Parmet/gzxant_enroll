package com.gzxant.service.enroll.enter;

import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.base.service.IBaseService;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.sun.tools.javac.comp.Enter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @param numbers
     * @return
     */
    EnrollEnter findbyIdEnterdate(String numbers);

    /**
     * 条件查询
     * @param list
     * @return
     */
    List<EnrollEnter> findByParasEnterdate(List<String> list);
    /**
     * 插入数据
     * @param enrollEnter
     * @return
     */
    boolean insertBean(EnrollEnter enrollEnter);

    /**
     * 导出数据
     * @param fileName
     * @return
     * @throws Exception
     */
    public boolean batchImport(String fileName) throws Exception;
}
