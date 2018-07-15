package com.gzxant.service.article;


import com.gzxant.base.service.IBaseService;
import com.gzxant.entity.enroll.article.EnrollArticle;
import com.gzxant.entity.enroll.article.dto.EnrollArticleDTO;

import java.util.List;

/**
 * <p>
 *     文章信息 服务类
 * </p>
 *
 * @author: Fatal
 * @date: 2018/7/12 0012 14:06
 */
public interface IEnrollArticleService extends IBaseService<EnrollArticle> {

    /**
     * 查询所有已发布的文章
     * @return
     */
    public List<EnrollArticleDTO> selectEnrollAricleList();

    /**
     *
     * @return
     */
    public EnrollArticleDTO selectById(Long id);



}
