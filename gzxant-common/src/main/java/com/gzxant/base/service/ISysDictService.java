package com.gzxant.base.service;

import com.gzxant.base.entity.SysDict;
import com.gzxant.base.vo.JsTree;
import com.gzxant.base.vo.PCAjaxVO;

import java.util.List;

/**
 *
 * @author chen
 * @date 2017/9/21
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系統数据字典
 */
public interface ISysDictService extends IBaseService<SysDict> {

    void insert(String dicKey, String dicValue, Long dicPid, String type, String desc, String sort, String invalid, String path);

    boolean update(Long id, String dicKey, String dicValue, String type, String desc, String sort, String invalid);

    List<JsTree> getDictTree();
    List<SysDict> getDictTree(String jkey);
    /**
     * 根据jkey获取其子项
     * @param code
     * @return
     */
    List<SysDict> getSub(String jkey);

    PCAjaxVO delete(Long id);


}
