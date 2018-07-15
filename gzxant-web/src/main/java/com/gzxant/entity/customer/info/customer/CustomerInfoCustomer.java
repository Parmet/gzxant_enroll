package com.gzxant.entity.customer.info.customer;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;

/**
 * <p>
 * Customer's information
 * </p>
 *
 * @author tecty
 * @since 2018-07-05
 */
@TableName("customer_info_customer")
public class CustomerInfoCustomer extends DataEntity<CustomerInfoCustomer> {

    private static final long serialVersionUID = 1L;

	@TableField("update_id")
	private Long updateId;
    /**
     * Name of the customer
     */
	private String name;
    /**
     * Gender of the customer
     */
	private String gender;
    /**
     * Telephone of the customer.
     */
	private String tel;
    /**
     * Foreign key to certificate table.
     */
	@TableField("certificate_id")
	private Long certificateId;
    /**
     * Whether this customer is blocked.
     */
	private String status;
    /**
     * Address of the customer.
     */
	private String address;
    /**
     * What's premitted to drive for this customer.
     */
	@TableField("drive_capability")
	private String driveCapability;
    /**
     * Email address of the customer.

     */
	private String email;
    /**
     * Name of emergency contact.
     */
	@TableField("emergency_contact")
	private String emergencyContact;
    /**
     * contact of the emergency contact.
     */
	@TableField("emergency_contact_tel")
	private String emergencyContactTel;


	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDriveCapability() {
		return driveCapability;
	}

	public void setDriveCapability(String driveCapability) {
		this.driveCapability = driveCapability;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyContactTel() {
		return emergencyContactTel;
	}

	public void setEmergencyContactTel(String emergencyContactTel) {
		this.emergencyContactTel = emergencyContactTel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CustomerInfoCustomer{" +
			"updateId=" + updateId +
			", name=" + name +
			", gender=" + gender +
			", tel=" + tel +
			", certificateId=" + certificateId +
			", status=" + status +
			", address=" + address +
			", driveCapability=" + driveCapability +
			", email=" + email +
			", emergencyContact=" + emergencyContact +
			", emergencyContactTel=" + emergencyContactTel +
			"}";
	}
}
