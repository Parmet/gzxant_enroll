package com.gzxant.common.entity.message;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;

/**
 * <p>
 * 短信表
 * </p>
 *
 * @author ycxiao
 * @since 2018-07-15
 */
@TableName("sys_message")
public class SysMessage extends DataEntity<SysMessage> {

    private static final long serialVersionUID = 1L;

	private String phone;
    /**
     * 验证码
     */
	private Integer code;
    /**
     * 状态 = { "UNUSED" : "未使用", "USED" : "已使用"}
     */
	private String state;
    /**
     * 类型（sys_dict表【MESSAGE_TYPE】）
     */
	private String type;
    /**
     * 短信内容
     */
	private String content;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysMessage{" +
			"phone=" + phone +
			", code=" + code +
			", state=" + state +
			", type=" + type +
			", content=" + content +
			"}";
	}
}
