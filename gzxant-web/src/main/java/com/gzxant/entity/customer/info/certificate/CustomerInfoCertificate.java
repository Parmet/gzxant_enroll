package com.gzxant.entity.customer.info.certificate;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Certificate table
 * </p>
 *
 * @author tecty
 * @since 2018-07-05
 */
@TableName("customer_info_certificate")
public class CustomerInfoCertificate extends DataEntity<CustomerInfoCertificate> {

    private static final long serialVersionUID = 1L;

	@TableField("update_id")
	private Long updateId;
    /**
     * Type of this certificate
     */
	private String type;
    /**
     * Id of provided certificate
     */
	private String number;
    /**
     * The issue day of the attachment.
     */
	@TableField("issue_date")
	private Date issueDate;
    /**
     * Until when this certificate will still be valid
     */
	@TableField("validity_period")
	private Date validityPeriod;
    /**
     * The upload attachment's location
     */
	private String attachment;


	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(Date validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CustomerInfoCertificate{" +
			"updateId=" + updateId +
			", type=" + type +
			", number=" + number +
			", issueDate=" + issueDate +
			", validityPeriod=" + validityPeriod +
			", attachment=" + attachment +
			"}";
	}
}
