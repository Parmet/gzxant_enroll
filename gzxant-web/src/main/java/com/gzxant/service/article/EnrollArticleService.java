package com.gzxant.service.article;

import com.gzxant.base.service.impl.BaseService;
import com.gzxant.dao.enroll.article.EnrollArticleDao;
import com.gzxant.entity.enroll.article.EnrollArticle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Fatal
 * @date: 2018/7/12 0012 14:45
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnrollArticleService extends BaseService<EnrollArticleDao, EnrollArticle> implements IEnrollArticleService {

}
