package com.ispan.model;

public class AqiDataBean {
	
	private Integer id;
	
	private String siteName;
	
	private Integer aqi;
	
	private Integer pm25;
	
	private String airStatus;

	public AqiDataBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getAqi() {
		return aqi;
	}

	public void setAqi(Integer aqi) {
		this.aqi = aqi;
	}

	public Integer getPm25() {
		return pm25;
	}

	public void setPm25(Integer pm25) {
		this.pm25 = pm25;
	}

	public String getAirStatus() {
		return airStatus;
	}

	public void setAirStatus(String airStatus) {
		this.airStatus = airStatus;
	}
	
}
