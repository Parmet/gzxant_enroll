package com.gzxant.common.dao.config;


import com.gzxant.base.dao.CrudDao;
import com.gzxant.common.entity.config.SysConfig;

import java.util.List;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统配置dao
 */
public interface SysConfigDao extends CrudDao<SysConfig> {
	List<SysConfig> getSub(String jkey);
}
