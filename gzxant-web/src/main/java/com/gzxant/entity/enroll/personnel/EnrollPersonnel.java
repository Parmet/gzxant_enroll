package com.gzxant.entity.enroll.personnel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;

/**
 * <p>
 * 参赛者信息
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@TableName("enroll_personnel")
public class EnrollPersonnel extends DataEntity<EnrollPersonnel> {

    private static final long serialVersionUID = 1L;

    /**
     * 参赛者姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;
    /**
     * 报名地点
     */
    private String place;
    /**
     * 工作单位
     */
    private String company;
    /**
     * 职业
     */
    private String profession;
    /**
     * 密码
     */
    private String password;
    /**
     * 歌唱风格
     */
    private String style;
    /**
     * 个人编号
     */
    private String numbers;

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EnrollPersonnel{" +
                "name=" + name +
                ", phone=" + phone +
                ", idCard=" + idCard +
                ", place=" + place +
                ", company=" + company +
                ", profession=" + profession +
                ", password=" + password +
                ", style=" + style +
                ", updateId=" + updateId +
                "}";
    }
}
