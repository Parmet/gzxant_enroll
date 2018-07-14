/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.gzxant.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gzxant.constant.Global;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据Entity类
 *
 * @param <T>
 */
public abstract class DataEntity<T extends Model> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     *  创建者
     */
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    protected Long createId;

    /**
     * 创建日期
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    protected Date createDate;

    /**
     * 更新者
     */
    @TableField(value = "update_id", fill = FieldFill.INSERT_UPDATE)
    protected Long updateId;

    /**
     * 更新日期
      */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    protected Date updateDate;

    /**
     * 删除标记（Y：正常；N：删除；A：审核；）
     */
    @TableField(value = "del_flag")
    protected String delFlag;

    /**
     * 备注
     */
    protected String remark;


    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public DataEntity() {
        super();
        this.delFlag = Global.DEL_FLAG_NORMAL;

    }

    public DataEntity(Long id) {
        super(id);
    }


    /**
     * 更新之前执行方法，需要手动调用
     */


    @Length(min = 0, max = 500, message = "备注信息长度必须介于 1 和 500 之间")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    @Length(min = 1, max = 1)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * <p>
     * 系统配置sys_dict
     * </p>
     *
     * @author ycxiao
     * @since 2018-05-14
     */
    @TableName("sys_config")
    public static class SysConfig extends TreeEntity<SysConfig> {

        private static final long serialVersionUID = 1L;

        private String jkey; //varchar(64) NULLkey
        private String jvalue; //varchar(1000) NULLvalue
        private String value; //varchar(1000) NULLvalue

        private String type;

        @TableField(exist = false)
        protected String name;

        @Override
        public String getName() {
            return name;
        }
        @Override
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 是否有效 Y 表示是   N表示否
         */
        private String invalid;

        /**
         * varchar(64)NULL所在公司id
         */
        @TableField(value = "sys_company_id")
        private String sysCompanyId;

        @Length(min = 0, max = 64, message = "key长度必须介于 1 和 64 之间")
        public String getJkey() {
            return jkey;
        }

        public void setJkey(String jkey) {
            this.jkey = jkey;
        }

        @Length(min = 0, max = 1000, message = "value长度必须介于 1 和 1000 之间")
        public String getJvalue() {
            return jvalue;
        }

        public void setJvalue(String jvalue) {
            this.jvalue = jvalue;
        }

        @Length(min = 0, max = 64, message = "公司id长度必须介于 1 和 64 之间")
        public String getSysCompanyId() {
            return sysCompanyId;
        }

        public void setSysCompanyId(String sysCompanyId) {
            this.sysCompanyId = sysCompanyId;
        }


        @Length(min = 0, max = 1, message = "类型长度为1")
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        protected Serializable pkVal() {
            return this.id;
        }

        @Length(min = 0, max = 1, message = "是否启动长度为1")
        public String getInvalid() {
            return invalid;
        }

        public void setInvalid(String invalid) {
            this.invalid = invalid;
        }

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "SysConfig{" +
                ", parentId=" + parentId +
                ", jkey=" + jkey +
                ", jvalue=" + jvalue +
                ", sort=" + sort +
                ", path=" + path +
                ", sysCompanyId=" + sysCompanyId +
                ", icon=" + icon +
                ", type=" + type +
                ", invalid=" + invalid +
                "}";
        }
    }
}
