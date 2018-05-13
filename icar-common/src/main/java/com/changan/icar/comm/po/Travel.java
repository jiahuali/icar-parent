package com.changan.icar.comm.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class Travel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8375984563745820001L;

	private String uuid;

	private String tuid;

	private BigDecimal mileage;

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

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "Travel [uuid=" + uuid + ", tuid=" + tuid + ", mileage=" + mileage + "]";
	}
	
	
}