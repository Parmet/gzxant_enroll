package com.gzxant.service.personnel;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.dao.enroll.personnel.EnrollPersonnelDao;
import com.gzxant.service.personnel.IEnrollPersonnelService;
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
public class EnrollPersonnelService extends BaseService<EnrollPersonnelDao, EnrollPersonnel> implements IEnrollPersonnelService {
    private static String prefix;

    private static int RESULT_SUCCESS = 1000;
    private static int PARARM_FAIL = 1001;
    private static int NOT_RESULT_SUCCESS = 1002;
    /**
     * 检测登录名是否重复
     *
     * @param name
     * @param id
     * @return
     */
    @Override
    public Boolean checkLoginName(String name, Long id) {
        if(StringUtils.isEmpty(name)){
            return null;
        }

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
        if(StringUtils.isEmpty(name)){
            return null;
        }
        if(StringUtils.isEmpty(password)){
            return null;
        }
        EnrollPersonnel enrollPersonnel = selectOne(Condition.create().eq("phone", name).in("password",password));
        return enrollPersonnel;
    }
    /**
     * 用户报名
     *
     * @param
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean insertBean(EnrollPersonnel param) {
        if (StringUtils.isEmpty(param.getName()) || StringUtils.isChinese(param.getName())) {
            return false;
        }
        if (StringUtils.isEmpty(param.getPassword())||!StringUtils.isPassword(param.getPassword())) {
            return false;
        }
        if (!StringUtils.isMobile(param.getPhone())||StringUtils.isEmpty(param.getPhone())) {
            return false;
        }
        if (!StringUtils.isIDCard(param.getIdCard())||StringUtils.isEmpty(param.getIdCard())) {
            return false;
        }
        if (StringUtils.isEmpty(param.getPlace())) {
            return false;
        }
        int count=selectAllcount();
        if(count<10){
            prefix="00";
        }else if(10<count||count<100){
            prefix="0";
        }else {
            prefix="";
        }
        param.setNumbers(prefix+(count+1));
        return insert(param);
    }
    /**
     * 获取所有用户数据总数
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int selectAllcount(){
        return selectList(null).size();
    }
    /**
     * 检查用户是否存在
     * @param numbers
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean checknumbers(String numbers){
        if(StringUtils.isEmpty(numbers)){
            return false;
        }
        EnrollPersonnel enrollPersonnel = selectOne(Condition.create().like("numbers",numbers));

        return enrollPersonnel != null && enrollPersonnel.getNumbers().equals(numbers);
    }
}
