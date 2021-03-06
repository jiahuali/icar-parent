package com.changan.icar.comm.po;

import java.io.Serializable;
import java.util.Date;

public class RunLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6369188604399922069L;

	private String uuid;

	private String tuid;

	private Date startTime;

	private Integer status;

	private String name;

	private String crashPath;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid == null ? null : tuid.trim();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCrashPath() {
		return crashPath;
	}

	public void setCrashPath(String crashPath) {
		this.crashPath = crashPath == null ? null : crashPath.trim();
	}

	@Override
	public String toString() {
		return "RunLog [uuid=" + uuid + ", tuid=" + tuid + ", startTime=" + startTime + ", status=" + status + ", name="
				+ name + ", crashPath=" + crashPath + "]";
	}
	
	
}