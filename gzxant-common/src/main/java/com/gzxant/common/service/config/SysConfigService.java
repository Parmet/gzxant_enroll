package com.gzxant.common.service.config;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.gzxant.base.service.impl.BaseService;
import com.gzxant.base.vo.JsTree;
import com.gzxant.base.vo.PCAjaxVO;
import com.gzxant.common.dao.config.SysConfigDao;
import com.gzxant.common.entity.config.SysConfig;
import com.gzxant.constant.Global;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author chen
 * @date 2017/8/9
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysConfigService extends BaseService<SysConfigDao, SysConfig> implements ISysConfigService {

    /**
     * 更新节点
     *
     * @param id
     * @param dicKey
     * @param dicValue
     * @param type
     * @param desc
     * @param sort
     * @param invalid
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean update(Long id, String dicKey, String dicValue, String type, String desc, String sort, String invalid, String value) {
        SysConfig sysDict = selectById(id);
        if (null == sysDict) {
            return false;
        }
        sysDict.setJkey(dicKey);
        sysDict.setJvalue(dicValue);
        if (!Strings.isNullOrEmpty(value)) {
            sysDict.setValue(value);
        }
        if (!Strings.isNullOrEmpty(sort)) {
            sysDict.setSort(Integer.parseInt(sort));
        }
        if (!Strings.isNullOrEmpty(type)) {
            sysDict.setType(type);
        }
        sysDict.setRemark(desc);
        sysDict.setInvalid(invalid);
        updateById(sysDict);
        return true;
    }

    @Override
    public List<JsTree> getConfigTree() {
        logger.info("查找字段树");
        List<SysConfig> sysDicts = this.baseMapper.selectList(Condition.create().orderBy("sort", true));
        List<JsTree> jts = Lists.newArrayList();
        for (SysConfig sysDict : sysDicts) {
            JsTree jt = new JsTree();
            jt.setId(sysDict.getId().toString());
            jt.setParent(sysDict.getParentId().compareTo(0L) > 0 ? sysDict.getParentId().toString() : "#");
            jt.setText(sysDict.getJvalue());
            if ("C".equals(sysDict.getType())) {
                jt.setIcon("fa fa-home");
            } else {
                jt.setIcon("glyphicon glyphicon-tint");
            }
            jts.add(jt);
        }
        return jts;

    }

    /**
     * @param dicKey
     * @param dicValue
     * @param dicPid
     * @param type
     * @param desc
     * @param sort
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void insert(String dicKey, String dicValue, Long dicPid, String type, String desc, String
            sort, String invalid, String path, String value) {
    	SysConfig sysDict = new SysConfig();

        if (null != dicPid) {
            sysDict.setParentId(dicPid);
        } else {
            sysDict.setParentId(0L);
        }
        sysDict.setJkey(dicKey);
        sysDict.setJvalue(dicValue);
        if (!Strings.isNullOrEmpty(value)) {
            sysDict.setValue(value);
        }
        if (!Strings.isNullOrEmpty(sort)) {
            sysDict.setSort(Integer.parseInt(sort));
        }
        if (!Strings.isNullOrEmpty(type)) {
            sysDict.setType(type);
        }
        sysDict.setRemark(desc);
        sysDict.setInvalid(invalid);
        insert(sysDict);

        if (Global.TOP_TREE_NODE.equals(sysDict.getParentId())) {
            sysDict.setPath(sysDict.getId() + ".");
        } else {

            sysDict.setPath(path + sysDict.getId() + ".");
        }

        updateById(sysDict);

    }


    /**
     * 删除节点和子节点
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public PCAjaxVO delete(Long id) {
        PCAjaxVO status = new PCAjaxVO(true);
        //是否为类，以及类下是否有引用
        SysConfig sysDict = selectById(id);

        if (sysDict != null) {
            //删除
            delete(Condition.create().like("path", sysDict.getPath(), SqlLike.RIGHT));

        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        status.setMessage("删除成功");
        return status;
    }

	@Override
	public List<SysConfig> getSub(String jkey) {
		List<SysConfig> sub = Lists.newArrayList();
		if (StringUtils.isBlank(jkey)) {
			return sub;
		}
		
		sub = this.baseMapper.getSub(jkey);
		return sub;
	}


}
