package com.gzxant.entity.enroll.enter;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

/**
 * <p>
 * 参赛者信息
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@TableName("enroll_enter")
public class EnrollEnter extends DataEntity<EnrollEnter> {

    private static final long serialVersionUID = 1L;


    /**
     * 地址
     */
    private String place;
    /**
     * 状态
     */
    private String state;
    /**
     * 参赛者编号id
     */
    private String numbers;
    /**
     * 分数
     */
    private String fraction;
    /**
     * 歌曲
     */
    private String song;
    /**
     * （缴费、报名、海选、复选、冠亚军）
     */
    private String type;
    /**
     * 姓名
     */
    private String name;
    @TableField("update_id")
    private Long updateId;


    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EnrollEnter{" +
                ", place=" + place +
                ", state=" + state +
                ", fraction=" + fraction +
                ", song=" + song +
                ", type=" + type +
                ", name=" + name +
                ", updateId=" + updateId +
                "}";
    }
}
