package com.gzxant.entity.customer.info.company;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tecty
 * @since 2018-07-05
 */
@TableName("customer_info_company")
public class CustomerInfoCompany extends DataEntity<CustomerInfoCompany> {

    private static final long serialVersionUID = 1L;

	@TableField("update_id")
	private Long updateId;
    /**
     * The name of the company.
     */
	private String name;
    /**
     * The abbreviation of the company name.
     */
	private String abbreviation;
    /**
     * Type of the company.
     */
	private String property;
    /**
     * Which category this company belong.
     */
	private String category;
    /**
     * Location of the company.
     */
	private String address;
    /**
     * The capital it registered to become a company.
     */
	@TableField("registered_capital")
	private String registeredCapital;
    /**
     * The date this company registered.
     */
	@TableField("registered_date")
	private Date registeredDate;
    /**
     * Name of the legal representative.
     */
	@TableField("legal_representative")
	private String legalRepresentative;
    /**
     * email of this company.
     */
	private String email;
    /**
     * license number after registration.
     */
	@TableField("license_number")
	private String licenseNumber;
    /**
     * file location of the attachment.
     */
	private String attachment;
    /**
     * contact's uuid.
     */
	@TableField("contact_id")
	private Integer contactId;


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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CustomerInfoCompany{" +
			"updateId=" + updateId +
			", name=" + name +
			", abbreviation=" + abbreviation +
			", property=" + property +
			", category=" + category +
			", address=" + address +
			", registeredCapital=" + registeredCapital +
			", registeredDate=" + registeredDate +
			", legalRepresentative=" + legalRepresentative +
			", email=" + email +
			", licenseNumber=" + licenseNumber +
			", attachment=" + attachment +
			", contactId=" + contactId +
			"}";
	}
}
