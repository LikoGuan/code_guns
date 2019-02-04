package com.stylefeng.guns.modular.foundation.transfer;

public class ServiceDistrictDto {

	private Integer id;

	private Integer orgid;
	
	private Integer districtId;

	private String comment;

	private String orgName;

	private String districtName;

	private String displayName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName() {
		this.displayName = String.format("%s--%s", districtName, orgName);
	}

}
