package com.changan.icar.comm.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4598738315810900428L;

	private String uuid;

	private String traUuid;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private BigDecimal altitude;

	private Float heading;

	private Date time;

	private String name;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getTraUuid() {
		return traUuid;
	}

	public void setTraUuid(String traUuid) {
		this.traUuid = traUuid == null ? null : traUuid.trim();
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	@Override
	public String toString() {
		return "Location [uuid=" + uuid + ", traUuid=" + traUuid + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", altitude=" + altitude + ", heading=" + heading + ", time=" + time + ", name=" + name + "]";
	}
	
}