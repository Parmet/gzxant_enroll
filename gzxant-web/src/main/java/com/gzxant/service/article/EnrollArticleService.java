package com.gzxant.service.article;

import com.alibaba.cloudapi.sdk.core.exception.SdkClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gzxant.base.service.impl.BaseService;
import com.gzxant.dao.article.EnrollArticleDao;
import com.gzxant.entity.article.EnrollArticle;
import com.gzxant.entity.article.dto.EnrollArticleDTO;
import com.gzxant.enums.HttpCodeEnum;
import com.gzxant.util.StringUtils;
import com.gzxant.utils.TextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * EnrollArticle 服务
 * @author: Fatal
 * @date: 2018/7/12 0012 14:45
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnrollArticleService extends BaseService<EnrollArticleDao, EnrollArticle> implements IEnrollArticleService {

    @Override
    public List<EnrollArticleDTO> selectEnrollAricleList() {
        List<EnrollArticle> enrollArticles = this.baseMapper.selectList(
                new EntityWrapper<EnrollArticle>().eq("is_release", 1));
        List<EnrollArticleDTO> enrollArticleDTOs = new ArrayList<EnrollArticleDTO>();
        if (enrollArticles != null) {
            for (EnrollArticle enrollArticle: enrollArticles) {
                EnrollArticleDTO enrollArticleDTO = new EnrollArticleDTO();
                enrollArticleDTO.setTitle(enrollArticle.getName());
                String subcontent = enrollArticle.getSubcontent();
                if (subcontent.length() > 16) {
                    subcontent = subcontent.substring(0,15);
                }
                enrollArticleDTO.setContent(subcontent);
                enrollArticleDTO.setImage(enrollArticle.getImage());
                enrollArticleDTOs.add(enrollArticleDTO);
            }
        }
        return enrollArticleDTOs;
    }

    @Override
    public EnrollArticleDTO selectById(Long id) {
        if (id == null) {
            throw new SdkClientException(HttpCodeEnum.INVALID_REQUEST.getMessage());
        }
        EnrollArticle enrollArticle = this.baseMapper.selectById(id);
        EnrollArticleDTO enrollArticleDTO = new EnrollArticleDTO();
        enrollArticleDTO.setTitle(enrollArticle.getName());
        enrollArticleDTO.setContent(enrollArticle.getContent());
        enrollArticleDTO.setImage(enrollArticle.getImage());
        enrollArticleDTO.setReleaseDate(enrollArticle.getReleaseDate());
        return enrollArticleDTO;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean insert(EnrollArticle enrollArticle) {
        Integer isRelease = enrollArticle.getIsRelease();
        if (isRelease != null && isRelease == 1) {
            enrollArticle.setReleaseDate(new Timestamp(System.currentTimeMillis()));
        }

        String content = enrollArticle.getContent();
        if (!StringUtils.isEmpty(content)) {
            //获取第一个图片url
            String image = enrollArticle.getImage();
            if (image == "") {
                image = TextUtil.catchImgUrl(content);
                enrollArticle.setImage(image);
            }
            //截取文章详情为简述
            String subcontent = TextUtil.subString(content);
            enrollArticle.setSubcontent(subcontent);
        }
        return super.insert(enrollArticle);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean updateById(EnrollArticle enrollArticle) {
        Integer isRelease = enrollArticle.getIsRelease();
        if (isRelease != null && isRelease == 1) {
            enrollArticle.setReleaseDate(new Timestamp(System.currentTimeMillis()));
        }

        String content = enrollArticle.getContent();
        if (!StringUtils.isEmpty(content)) {
            //获取第一个图片url
            String image = enrollArticle.getImage();
            if (image == "") {
                image = TextUtil.catchImgUrl(content);
                enrollArticle.setImage(image);
            }
            //截取文章详情为简述
            String subcontent = TextUtil.subString(content);
            enrollArticle.setSubcontent(subcontent);
        }
        return super.updateById(enrollArticle);
    }
}
