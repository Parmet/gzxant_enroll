package com.gzxant.common.service.message;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.base.service.impl.BaseService;
import com.gzxant.common.dao.message.SysMessageDao;
import com.gzxant.common.entity.message.SysMessage;
import com.gzxant.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 短信表 服务实现类
 * </p>
 *
 * @author ycxiao
 * @since 2018-07-15
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysMessageService extends BaseService<SysMessageDao, SysMessage> implements ISysMessageService {

    @Override
    @Transactional(readOnly = false)
    public boolean insert(SysMessage entity) {
        if (entity == null || entity.getPhone() == null
                || entity.getType() == null) {
            return false;
        }

        if (StringUtils.isBlank(entity.getType())) {
            entity.setType("UNUSED");
        }

        // 查询当前手机所有未使用的短信信息
        List<SysMessage> olds = this
                .selectList(Condition
                        .create()
                        .eq("phone", entity.getPhone())
                        .eq("state", "UNUSED"));

        // 插入当前的短信信息
        super.insert(entity);

        // 未使用的短信信息更新为已使用
        if (olds == null || olds.isEmpty()) {
            return true;
        }

        for (SysMessage old : olds) {
            old.setState("USED");
        }

        this.updateBatchById(olds);

        return true;
    }

}
