package com.changan.icar.comm.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class Device implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1137143679617764358L;

	private String tuid;

	private String uuid;

	private String name;

	private Integer type;

	private String model;

	private Integer isDefault;

	private Integer status;

	private BigDecimal oilAmount;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private BigDecimal speed;

	private BigDecimal altitude;

	private Float heading;

	private String carNumber;

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid == null ? null : tuid.trim();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getOilAmount() {
		return oilAmount;
	}

	public void setOilAmount(BigDecimal oilAmount) {
		this.oilAmount = oilAmount;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getSpeed() {
		return speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public BigDecimal getAltitude() {
		return altitude;
	}

	public void setAltitude(BigDecimal altitude) {
		this.altitude = altitude;
	}

	public Float getHeading() {
		return heading;
	}

	public void setHeading(Float heading) {
		this.heading = heading;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber == null ? null : carNumber.trim();
	}

	@Override
	public String toString() {
		return "Device [tuid=" + tuid + ", uuid=" + uuid + ", name=" + name + ", type=" + type + ", model=" + model
				+ ", isDefault=" + isDefault + ", status=" + status + ", oilAmount=" + oilAmount + ", longitude="
				+ longitude + ", latitude=" + latitude + ", speed=" + speed + ", altitude=" + altitude + ", heading="
				+ heading + ", carNumber=" + carNumber + "]";
	}
	
	
}