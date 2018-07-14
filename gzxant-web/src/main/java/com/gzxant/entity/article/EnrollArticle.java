package com.gzxant.entity.article;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gzxant.base.entity.DataEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
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
    @NotEmpty(message = "文章标题不能为空")
    private String name;

    /**
     * 文章内容
     */
    @NotEmpty(message = "文章内容不能为空")
    private String content;

    /**
     * 是否发布 1:发布 0:未发布
     */
    @TableField("is_release")
    private Integer isRelease;

    /**
     * 列表图片
     */
    private String image;

    /**
     * 简述
     */
    private String subcontent;

    /**
     * 文章发布时间
     */
    @TableField("release_date")
    private Timestamp releaseDate;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubcontent() {
        return subcontent;
    }

    public void setSubcontent(String subcontent) {
        this.subcontent = subcontent;
    }

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
                ", image='" + image + '\'' +
                ", subcontent='" + subcontent + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
