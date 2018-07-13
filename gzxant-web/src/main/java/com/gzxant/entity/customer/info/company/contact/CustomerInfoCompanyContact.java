package com.gzxant.entity.customer.info.company.contact;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author tecty
 * @since 2018-07-05
 */
@TableName("customer_info_company_contact")
public class CustomerInfoCompanyContact extends BaseEntity<CustomerInfoCompanyContact> {

    private static final long serialVersionUID = 1L;

    /**
     * contact name.
     */
	private String name;
    /**
     * telephone of contact.
     */
	private String tel;
    /**
     * Id number of the contact.
     */
	@TableField("id_num")
	private String idNum;
    /**
     * Position of contact.
     */
	private String position;
    /**
     * email of contact.
     */
	private String email;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CustomerInfoCompanyContact{" +
			"name=" + name +
			", tel=" + tel +
			", idNum=" + idNum +
			", position=" + position +
			", email=" + email +
			"}";
	}
}
