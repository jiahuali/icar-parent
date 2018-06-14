package com.changan.icar.wechat;

import java.math.BigDecimal;
import java.util.Date;

import com.changan.icar.comm.po.Location;

public class OnlineDevice {

	private String tuid;

	private Date lastUpdate;

	private Boolean online;

	private Location location;

	private String travelUuid;

	private BigDecimal oilAmountStart;

	private BigDecimal oilAmountNow;

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTravelUuid() {
		return travelUuid;
	}

	public void setTravelUuid(String travelUuid) {
		this.travelUuid = travelUuid;
	}

	public BigDecimal getOilAmountStart() {
		return oilAmountStart;
	}

	public void setOilAmountStart(BigDecimal oilAmountStart) {
		this.oilAmountStart = oilAmountStart;
	}

	public BigDecimal getOilAmountNow() {
		return oilAmountNow;
	}

	public void setOilAmountNow(BigDecimal oilAmountNow) {
		this.oilAmountNow = oilAmountNow;
	}

}
