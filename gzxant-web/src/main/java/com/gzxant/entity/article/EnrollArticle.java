package com.gzxant.entity.enroll.article;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *     文章信息
 * </p>
 *
 * @author: Fatal
 * @date: 2018/7/12 0012 12:56
 */
@TableName("enroll_article")
public class EnrollArticle extends DataEntity<EnrollArticle> {

    /**
     * 文章标题
     */
    private String name;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 是否发布 1:发布 0:未发布
     */
    @TableField("is_release")
    private Integer isRelease;

    /**
     * 文章发布时间
     */
    @TableField("release_date")
    private Timestamp releaseDate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getIsReleaseStr() {
        return isRelease==1?"已发布":"未发布";
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    public String getReleaseDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
        if (releaseDate == null) {
            return null;
        }
        return format.format(releaseDate);
    }

    public Date getDateReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "EnrollArticle{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", isRelease=" + isRelease +
                ", releaseDate=" + releaseDate +
                '}';
    }


}
