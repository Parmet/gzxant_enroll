package com.gzxant.common.dao.dict;


import com.gzxant.base.dao.TreeDao;
import com.gzxant.common.entity.dict.SysDict;

import java.util.List;

/**
 * Created by chen on 2017/4/10.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统数据字典dao
 */
public interface SysDictDao extends TreeDao<SysDict> {

	List<SysDict> getSub(String jkey);
}
