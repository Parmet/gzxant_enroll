package com.gzxant.entity.enroll.vedio;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 *     视频实体类
 * </p>
 * @author jadenziv
 *
 */
@TableName("enroll_video")
public class EnrollVedio extends DataEntity<EnrollVedio> {

    private static final long serialVersionUID = 1L;

    /**
     * 视频详细
     */
    private String name;

    @TableField("video_url")
    private String vedioUrl;

    @TableField("release_date")
    private Date releaseDate = new Date();


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

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


}
