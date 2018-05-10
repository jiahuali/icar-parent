package com.changan.icar.comm.po;

public class Account {
	private String uuid;

	private String username;

	private Integer status;

	private String phone;

	private String pushTag;

	private String pushAlias;

	private String defaultDevice;

	private Integer type;

	private Integer userFrom;

	private String picUrl;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPushTag() {
		return pushTag;
	}

	public void setPushTag(String pushTag) {
		this.pushTag = pushTag == null ? null : pushTag.trim();
	}

	public String getPushAlias() {
		return pushAlias;
	}

	public void setPushAlias(String pushAlias) {
		this.pushAlias = pushAlias == null ? null : pushAlias.trim();
	}

	public String getDefaultDevice() {
		return defaultDevice;
	}

	public void setDefaultDevice(String defaultDevice) {
		this.defaultDevice = defaultDevice == null ? null : defaultDevice.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Integer userFrom) {
		this.userFrom = userFrom;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl == null ? null : picUrl.trim();
	}

	@Override
	public String toString() {
		return "Account [uuid=" + uuid + ", username=" + username + ", status=" + status + ", phone=" + phone
				+ ", pushTag=" + pushTag + ", pushAlias=" + pushAlias + ", defaultDevice=" + defaultDevice + ", type="
				+ type + ", userFrom=" + userFrom + ", picUrl=" + picUrl + "]";
	}

}