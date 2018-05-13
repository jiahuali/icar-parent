package com.changan.icar.comm.po;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7474216849178886401L;

	private String uuid;

	private String username;

	private String ideUuid;

	private Date time;

	private Integer type;

	private String content;

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

	public String getIdeUuid() {
		return ideUuid;
	}

	public void setIdeUuid(String ideUuid) {
		this.ideUuid = ideUuid == null ? null : ideUuid.trim();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "Feedback [uuid=" + uuid + ", username=" + username + ", ideUuid=" + ideUuid + ", time=" + time
				+ ", type=" + type + ", content=" + content + "]";
	}
	
}